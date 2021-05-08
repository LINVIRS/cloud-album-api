package com.ssy.api.utils;


import com.ffmpeg.common.FFMpegExceptionn;
import com.ffmpeg.common.audio.AudioOperation;
import com.ffmpeg.common.common.ProcessCommand;
import com.ffmpeg.common.response.Result;
import com.ffmpeg.common.utils.BaseFileUtil;
import com.ffmpeg.common.utils.StrUtils;
import com.ffmpeg.common.video.VideoOperation;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VideoUtil {
//    static String ffmpegPath = "/opt/homebrew/Cellar/ffmpeg/4.3.2_4/bin/ffmpeg";
    static String ffmpegPath = "/usr/bin/ffmpeg";


    // 创建VideoOperation对象
    private static final VideoOperation ffmpeg = VideoOperation.builder(ffmpegPath);
    // 创建AudioOperation对象
    private static final AudioOperation ffmpegAudio = AudioOperation.builder(ffmpegPath);

    /**
     * 合并视频文件
     * flv文件合并成
     *
     * @param videoPathList
     */
    public static void mergeVideo(List<String> videoPathList) {
        if (videoPathList.size() > 1) {
            String txtPath;
            try {
                int index = videoPathList.get(0).lastIndexOf(".");
                String newMergePath = videoPathList.get(0).substring(0, index) + "new" + videoPathList.get(0).substring(index);
                txtPath = videoPathList.get(0).substring(0, index) + ".txt";
                FileOutputStream fos = new FileOutputStream(txtPath);
                for (String path : videoPathList) {
                    fos.write(("file '" + path + "'\r\n").getBytes());
                }
                fos.close();

                StringBuffer command = new StringBuffer();
                command.append(ffmpegPath);
                command.append(" -f");
                command.append(" concat");
                command.append(" -safe");
                command.append(" 0");
                command.append(" -i ");
                command.append(txtPath);
                command.append(" -c");
                command.append(" copy ");// -c copy 避免解码，提高处理速度
                command.append(newMergePath);
                Runtime.getRuntime().exec(String.valueOf(command));

                // 删除产生的临时用来存放每个视频文件路径的txt文件
                File txtFile = new File(txtPath);
                txtFile.delete();

                // 删除原视频文件
                for (String filePath : videoPathList) {
                    File file = new File(filePath);
                    file.delete();
                }

                // 合成的新视频文件重命名为原视频文件的第一个文件名
                File newFile = new File(newMergePath);
                File oldFile = new File(videoPathList.get(0));
                newFile.renameTo(oldFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 将视频中的音频替换为指定音频
     *
     * @param audioInputPath  音频资源路径
     * @param videoInputPath  输入视频路径
     * @param videoOutputPath 输出视频路径
     * @param videoTime       视频时长
     * @throws IOException
     */
    public static Result convertor(String audioInputPath, String videoInputPath, String videoOutputPath, int videoTime) throws IOException {
        return ffmpeg.mergeVideoAndBgmWithOrigin(audioInputPath, videoInputPath
                , videoOutputPath, videoTime);
    }

    /**
     * 合并视频文件
     *
     * @param videoListFile
     * @param videoOutPath
     * @return
     */
    public static Result mergeMultiVideosByFile(File videoListFile, String videoOutPath) {
        return ffmpeg.mergeMultiVideosByFile(videoListFile, videoOutPath);
    }

    /**
     * 视频合并音频，给视频加上背景音乐，并不保留视频原声
     *
     * @param videoInputPath
     * @param bgmInputPath
     * @return
     */
    public static Result convertorWithBgmNoOriginCommon(String videoInputPath,String videoInputPathNew, String bgmInputPath) {
        return ffmpeg.convertorWithBgmNoOriginCommon(videoInputPath, videoInputPathNew, videoInputPath, bgmInputPath, getVideoTime(videoInputPath));
    }

    /**
     * 转换视频格式
     *
     * @param inputVideo
     * @param outputVideo
     */
    public static Result videoConvert(String inputVideo, String outputVideo) {
        return ffmpeg.videoConvert(inputVideo, outputVideo);
    }

    /**
     * 获取视频的封面图
     *
     * @param inputVideo
     * @param coverOut
     * @return
     */
    public static Result getVideoCoverImg(String inputVideo, String coverOut) {
        return ffmpeg.getVideoCoverImg(inputVideo, coverOut);
    }

    /**
     * 去除视频的音频
     *
     * @param inputVideo
     * @param outputVideo
     * @return
     */
    public static Result wipeAudio(String inputVideo, String outputVideo) {
        return ffmpeg.wipeAudio(inputVideo, outputVideo);
    }

    public static Result transFormatAudio(String inputAudio, String outAudio) {
        return ffmpegAudio.transFormatAudio(inputAudio, outAudio);
    }

    /**
     * 获取视频总时间
     *
     * @param video_path 视频路径
     * @return
     */
    public static int getVideoTime(String video_path) {
        List<String> commands = new java.util.ArrayList<>();
        commands.add(ffmpegPath);
        commands.add("-i");
        commands.add(video_path);
        try {
            ProcessBuilder builder = new ProcessBuilder();
            builder.command(commands);
            final Process p = builder.start();

            //从输入流中读取视频信息
            BufferedReader br = new BufferedReader(new InputStreamReader(p.getErrorStream()));
            StringBuffer sb = new StringBuffer();
            String line = "";
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            br.close();

            //从视频信息中解析时长
            String regexDuration = "Duration: (.*?), start: (.*?), bitrate: (\\d*) kb\\/s";
            Pattern pattern = Pattern.compile(regexDuration);
            Matcher m = pattern.matcher(sb.toString());
            if (m.find()) {
                int time = getTimelen(m.group(1));
                System.out.println(video_path + ",视频时长：" + time + ", 开始时间：" + m.group(2) + ",比特率：" + m.group(3) + "kb/s");
                return time;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }

    //格式:"00:00:10.68"
    private static int getTimelen(String timelen) {
        int min = 0;
        String[] strs = timelen.split(":");
        if (strs[0].compareTo("0") > 0) {
            min += Integer.parseInt(strs[0]) * 60 * 60;//秒
        }
        if (strs[1].compareTo("0") > 0) {
            min += Integer.parseInt(strs[1]) * 60;
        }
        if (strs[2].compareTo("0") > 0) {
            min += Math.round(Float.parseFloat(strs[2]));
        }
        return min;
    }

    public static File multipartToFile(MultipartFile multipart) {
        File convFile = new File(Objects.requireNonNull(multipart.getOriginalFilename()));
        try {
            multipart.transferTo(convFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return convFile;
    }

    /**
     * 图片生成视频
     *
     * @param picture
     * @param videoOutPath
     * @return
     */
    public static Result pictureToVideo(File picture, String videoOutPath) {
        if (StrUtils.checkBlank(videoOutPath)) {
            throw new FFMpegExceptionn("videoOutPath must be valid");
        } else if (picture.exists() && picture.isFile()) {
            BaseFileUtil.checkAndMkdir(videoOutPath);
            List<String> commands = new ArrayList();
            commands.add(ffmpegPath);
            commands.add("-f");
            commands.add("concat");
            commands.add("-safe");
            commands.add("0");
            commands.add("-i");
            commands.add(picture.getAbsolutePath());
            commands.add("-vsync");
            commands.add("vfr");
            commands.add("-pix_fmt");
            commands.add("yuv420p");
            commands.add(videoOutPath);
            return ProcessCommand.start(commands);
        } else {
            throw new FFMpegExceptionn("videoListFile not found");
        }
    }
}
