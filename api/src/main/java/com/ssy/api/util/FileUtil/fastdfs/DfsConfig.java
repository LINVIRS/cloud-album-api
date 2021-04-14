package com.ssy.api.util.FileUtil.fastdfs;

import com.github.tobato.fastdfs.FdfsClientConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.context.annotation.Import;
import org.springframework.jmx.support.RegistrationPolicy;

/**
 * @ClassName: DfsConfig @Description: TODO @Author: WangLinLIN @Date: 2021/04/10 18:13
 * 27  @Version: V1.0
 */
@Configuration
@Import(FdfsClientConfig.class)
// Jmx重复注册bean的问题
@EnableMBeanExport(registration = RegistrationPolicy.IGNORE_EXISTING)
public class DfsConfig {}
