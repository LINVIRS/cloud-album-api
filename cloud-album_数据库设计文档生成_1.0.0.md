# 数据库设计文档

**数据库名：** cloud-album

**文档版本：** 1.0.0

**文档描述：** 数据库设计文档生成

| 表名                  | 说明       |
| :---: | :---: |
| [albums](#albums) | 相册表 |
| [face](#face) | 人脸表 |
| [face_store](#face_store) | 人脸库表 |
| [identify](#identify) | 识别表 |
| [photo](#photo) | 照片表 |
| [share_album](#share_album) | 分享表 |
| [tag](#tag) | 标签表 |
| [ud_record](#ud_record) | 上传下载记录表 |
| [user](#user) | 用户表 |
| [video](#video) | 视频表 |

**表名：** <a id="albums">albums</a>

**说明：** 相册表

**数据列：**

| 序号 | 名称 | 数据类型 |  长度  | 小数位 | 允许空值 | 主键 | 默认值 | 说明 |
| :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: |
|  1   | id |   int   | 10 |   0    |    N     |  Y   |       |   |
|  2   | user_id |   int   | 10 |   0    |    Y     |  N   |       | 用户id  |
|  3   | type |   int   | 10 |   0    |    Y     |  N   |       | 类型0私有1公有  |
|  4   | create_type |   int   | 10 |   0    |    Y     |  N   |       | 创建类型自助创建:0,ai生成1  |
|  5   | photo_id |   varchar   | 255 |   0    |    N     |  N   |       | 照片id数组逗号隔开1,2,3,4,5  |
|  6   | cover |   varchar   | 255 |   0    |    Y     |  N   |       | 封面  |
|  7   | name |   varchar   | 255 |   0    |    Y     |  N   |       | 相册名(分类不能修改)  |
|  8   | photo_number |   varchar   | 255 |   0    |    Y     |  N   |       | 照片数  |
|  9   | total_capacity |   int   | 10 |   0    |    Y     |  N   |       | 总容量  |
|  10   | tag_id |   int   | 10 |   0    |    Y     |  N   |       | 自助创建相册为0  |
|  11   | create_time |   timestamp   | 19 |   0    |    Y     |  N   |       | 创建时间  |
|  12   | update_time |   timestamp   | 19 |   0    |    Y     |  N   |       | 更新时间  |
|  13   | is_delete |   int   | 10 |   0    |    N     |  N   |       | 是否删除  |

**表名：** <a id="face">face</a>

**说明：** 人脸表

**数据列：**

| 序号 | 名称 | 数据类型 |  长度  | 小数位 | 允许空值 | 主键 | 默认值 | 说明 |
| :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: |
|  1   | face_id |   int   | 10 |   0    |    N     |  Y   |       | 人脸ID  |
|  2   | url |   varchar   | 255 |   0    |    Y     |  N   |       | 人脸url  |
|  3   | face_rectangle |   varchar   | 255 |   0    |    Y     |  N   |       | 人脸矩形区域，顺序为左上坐标和右下坐标  |
|  4   | name |   varchar   | 255 |   0    |    Y     |  N   |       | 名称  |
|  5   | photo_id |   int   | 10 |   0    |    Y     |  N   |       | 图片ID  |
|  6   | confidence |   float   | 256 |   0    |    Y     |  N   |       | 置信度  |
|  7   | extra_info |   varchar   | 255 |   0    |    Y     |  N   |       | 额外信息  |
|  8   | create_time |   timestamp   | 19 |   0    |    Y     |  N   |       | 创建时间  |
|  9   | update_time |   timestamp   | 19 |   0    |    Y     |  N   |       | 更新时间  |

**表名：** <a id="face_store">face_store</a>

**说明：** 

**数据列：**

| 序号 | 名称 | 数据类型 |  长度  | 小数位 | 允许空值 | 主键 | 默认值 | 说明 |
| :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: |
|  1   | face_store_id |   int   | 10 |   0    |    N     |  Y   |       | 人脸库ID  |
|  2   | album_id |   int   | 10 |   0    |    Y     |  N   |       |   |
|  3   | name |   varchar   | 255 |   0    |    Y     |  N   |       | 名称  |
|  4   | description |   varchar   | 255 |   0    |    Y     |  N   |       | 描述  |
|  5   | create_time |   timestamp   | 19 |   0    |    Y     |  N   |       | 创建时间  |
|  6   | update_time |   timestamp   | 19 |   0    |    Y     |  N   |       | 更新时间  |
|  7   | face_id |   varchar   | 255 |   0    |    Y     |  N   |       | 人脸id，逗号隔开  |

**表名：** <a id="identify">identify</a>

**说明：** 分类识别表

**数据列：**

| 序号 | 名称 | 数据类型 |  长度  | 小数位 | 允许空值 | 主键 | 默认值 | 说明 |
| :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: |
|  1   | id |   int   | 10 |   0    |    N     |  Y   |       | 逐渐id  |
|  2   | type |   varchar   | 255 |   0    |    Y     |  N   |       | 分类类型  |
|  3   | identify_result |   varchar   | 255 |   0    |    Y     |  N   |       | 分类识别结果  |
|  4   | user_id |   int   | 10 |   0    |    Y     |  N   |       | 用户id  |
|  5   | photo_id |   int   | 10 |   0    |    Y     |  N   |       | 照片id  |
|  6   | create_time |   timestamp   | 19 |   0    |    Y     |  N   |       | 创建时间  |
|  7   | update_time |   timestamp   | 19 |   0    |    Y     |  N   |       | 更新时间  |
|  8   | is_delete |   int   | 10 |   0    |    Y     |  N   |       | 是否删除  |

**表名：** <a id="photo">photo</a>

**说明：** 相册表

**数据列：**

| 序号 | 名称 | 数据类型 |  长度  | 小数位 | 允许空值 | 主键 | 默认值 | 说明 |
| :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: |
|  1   | id |   int   | 10 |   0    |    N     |  Y   |       |   |
|  2   | url |   varchar   | 255 |   0    |    Y     |  N   |       | 图片地址  |
|  3   | is_upload |   int   | 10 |   0    |    Y     |  N   |   0    | 是否上传0未上传1为上传  |
|  4   | tag_id |   varchar   | 255 |   0    |    Y     |  N   |       | 标签id  |
|  5   | longitude |   double   | 23 |   0    |    Y     |  N   |       | 经度  |
|  6   | latitude |   double   | 23 |   0    |    Y     |  N   |       | 纬度  |
|  7   | user_id |   int   | 10 |   0    |    Y     |  N   |       | 用户id  |
|  8   | create_time |   timestamp   | 19 |   0    |    Y     |  N   |       | 创建时间  |
|  9   | update_time |   timestamp   | 19 |   0    |    Y     |  N   |       | 更新时间  |
|  10   | is_delete |   int   | 10 |   0    |    Y     |  N   |       | 是否删除  |
|  11   | photo_name |   varchar   | 255 |   0    |    Y     |  N   |       | 图片名称  |
|  12   | photo_size |   varchar   | 255 |   0    |    Y     |  N   |       | 图片尺寸  |
|  13   | latitude_ref |   varchar   | 255 |   0    |    Y     |  N   |       | 维度方向  |
|  14   | longitude_ref |   varchar   | 255 |   0    |    Y     |  N   |       | 精度方向  |
|  15   | height |   varchar   | 255 |   0    |    Y     |  N   |       | 高度  |
|  16   | width |   varchar   | 255 |   0    |    Y     |  N   |       | 宽度  |
|  17   | time |   timestamp   | 19 |   0    |    Y     |  N   |       | 照片拍摄时间  |

**表名：** <a id="share_album">share_album</a>

**说明：** 分享表

**数据列：**

| 序号 | 名称 | 数据类型 |  长度  | 小数位 | 允许空值 | 主键 | 默认值 | 说明 |
| :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: |
|  1   | id |   int   | 10 |   0    |    N     |  Y   |       | 共享相册主键  |
|  2   | creator_id |   int   | 10 |   0    |    N     |  N   |       | 创建者id  |
|  3   | join_user_id |   varchar   | 255 |   0    |    Y     |  N   |       | 加入人id  |
|  4   | album_id |   int   | 10 |   0    |    N     |  N   |       | 相册id  |
|  5   | create_time |   datetime   | 19 |   0    |    N     |  N   |       | 创建时间  |
|  6   | update_time |   datetime   | 19 |   0    |    N     |  N   |       | 修改时间  |
|  7   | is_delete |   int   | 10 |   0    |    N     |  N   |       | 是否删除  |
|  8   | key_word |   varchar   | 255 |   0    |    N     |  N   |       | 共享口令  |

**表名：** <a id="tag">tag</a>

**说明：** 

**数据列：**

| 序号 | 名称 | 数据类型 |  长度  | 小数位 | 允许空值 | 主键 | 默认值 | 说明 |
| :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: |
|  1   | id |   int   | 10 |   0    |    N     |  Y   |       | 主键id  |
|  2   | name |   varchar   | 255 |   0    |    Y     |  N   |       | 标签名称  |
|  3   | description |   varchar   | 255 |   0    |    Y     |  N   |       | 描述  |
|  4   | create_time |   timestamp   | 19 |   0    |    Y     |  N   |       | 创建时间  |
|  5   | update_time |   timestamp   | 19 |   0    |    Y     |  N   |       | 更新下时间  |
|  6   | is_delete |   int   | 10 |   0    |    Y     |  N   |       | 是否删除  |
|  7   | user_id |   int   | 10 |   0    |    Y     |  N   |       | 用户id  |

**表名：** <a id="ud_record">ud_record</a>

**说明：** 上传下载表

**数据列：**

| 序号 | 名称 | 数据类型 |  长度  | 小数位 | 允许空值 | 主键 | 默认值 | 说明 |
| :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: |
|  1   | id |   int   | 10 |   0    |    N     |  Y   |       |   |
|  2   | type |   int   | 10 |   0    |    Y     |  N   |       | 0上传1下载  |
|  3   | status |   int   | 10 |   0    |    Y     |  N   |       | 1完成2失败3取消  |
|  4   | photo_id |   varchar   | 255 |   0    |    Y     |  N   |       | 照片id数组1,2,3,4,5  |
|  5   | user_id |   int   | 10 |   0    |    Y     |  N   |       | 用户id  |
|  6   | create_time |   timestamp   | 19 |   0    |    Y     |  N   |       | 创建时间  |
|  7   | update_time |   timestamp   | 19 |   0    |    Y     |  N   |       | 更新时间  |
|  8   | is_delete |   int   | 10 |   0    |    Y     |  N   |       | 0为删除1删除  |

**表名：** <a id="user">user</a>

**说明：** 用户表

**数据列：**

| 序号 | 名称 | 数据类型 |  长度  | 小数位 | 允许空值 | 主键 | 默认值 | 说明 |
| :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: |
|  1   | id |   int   | 10 |   0    |    N     |  Y   |       | id  |
|  2   | phone |   varchar   | 255 |   0    |    Y     |  N   |       | 手机  |
|  3   | account |   varchar   | 255 |   0    |    Y     |  N   |       | 账户  |
|  4   | password |   varchar   | 255 |   0    |    Y     |  N   |       | 密码  |
|  5   | slat |   varchar   | 255 |   0    |    Y     |  N   |       | 盐值  |
|  6   | sex |   int   | 10 |   0    |    Y     |  N   |       | 0为女1为男  |
|  7   | IDCard |   varchar   | 255 |   0    |    Y     |  N   |       | 身份证  |
|  8   | nickname |   varchar   | 255 |   0    |    Y     |  N   |       | 名称  |
|  9   | avatar |   varchar   | 255 |   0    |    Y     |  N   |       | 头像地址  |
|  10   | level |   int   | 10 |   0    |    Y     |  N   |       | 等级  |
|  11   | address |   varchar   | 255 |   0    |    Y     |  N   |       | 地址  |
|  12   | create_time |   timestamp   | 19 |   0    |    Y     |  N   |   CURRENT_TIMESTAMP    | 创建时间  |
|  13   | update_time |   timestamp   | 19 |   0    |    Y     |  N   |   CURRENT_TIMESTAMP    | 更新时间  |
|  14   | is_delete |   int   | 10 |   0    |    Y     |  N   |   0    | 0未删除1删除  |

**表名：** <a id="video">video</a>

**说明：** 视频表

**数据列：**

| 序号 | 名称 | 数据类型 |  长度  | 小数位 | 允许空值 | 主键 | 默认值 | 说明 |
| :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: |
|  1   | id |   int   | 10 |   0    |    N     |  Y   |       |   |
|  2   | user_id |   int   | 10 |   0    |    Y     |  N   |       | 用户id  |
|  3   | name |   varchar   | 255 |   0    |    Y     |  N   |       | 视频名称  |
|  4   | cover |   varchar   | 255 |   0    |    Y     |  N   |       | 封面图  |
|  5   | url |   varchar   | 255 |   0    |    Y     |  N   |       | 视频地址  |
|  6   | duration |   int   | 10 |   0    |    N     |  N   |       | 视频时长  |
|  7   | create_time |   timestamp   | 19 |   0    |    Y     |  N   |       |   |
|  8   | update_time |   timestamp   | 19 |   0    |    Y     |  N   |       |   |
