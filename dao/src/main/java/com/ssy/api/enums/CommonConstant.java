package com.ssy.api.enums;

/**
 * @program: client_api
 * @description: 删除标志
 * @author: wl
 * @created: 2020/07/09 14:12
 */
public class CommonConstant {
  /** 正常未被删除 */
  public static final byte DELFlag = 0;
  /** 被删除 */
  public static final byte DELETE_DELFlag = 1;

  // topic 表中枚举
  /** 被置顶 */
  public static final Integer IS_TOP = 1;
  /** 未置顶 */
  public static final Integer NO_TOP = 0;
  /** 推荐 */
  public static final Integer IS_RECOMMEND = 1;

  /** 未推荐 */
  public static final Integer NO_RECOMMEND = 0;

  /** 初始热度 */
  public static final Integer HOT = 0;

  // 赛事未审核
  public static final byte ACTIVE_NO_AUDIT = 0;
  // 赛事通过
  public static final byte ACTIVE_IS_PASSED = 1;
  // 赛事未通过
  public static final byte ACTIVE_NO_PASSED = 2;
  // 赛事已结算
  public static final byte ACTIVE_HAVE_FINSHED = 3;

  // 精选未审核
  public static final byte CHOICENESS_NO_AUDIT = 0;
  // 精选通过
  public static final byte CHOICENESS_IS_PASSED = 1;
  // 精选未通过
  public static final byte CHOICENESS_NO_PASSED = 2;
  // 精选已结算
  public static final byte CHOICENESS_HAVE_FINSHED = 3;

  // 运动四个字段 0跑步 1健走 2骑行 3登山
  public static final byte RUN_TYPE = 0;
  public static final byte WALKING_TYPE = 1;
  public static final byte CYCLING_TYPE = 2;
  public static final byte MOUNTAINEERING_TYPE = 3;

  // 开启计划
  public static final byte PLAN_IS_OPEN = 0;
  // 计划关闭
  public static final byte PLAN_IS_CLOSE = 1;
  // 帖子隐藏
  public static final byte CHOICENESS_ISNOT_SHOW = 0;
  // 帖子显示
  public static final byte CHOICENESS_IS_SHOW = 1;

  // 计划未完成
  public static final byte PLAN_IS_NOT_FINISH = 0;
  // 计划完成
  public static final byte PLAN_IS_FINISH = 1;

  // 消息
  // 全体消息 用户id为0
  public static final byte All_USER_MESSAGE = 0;
  // 系统消息
  public static final byte MESSAGE_SYSTEM = 0;
  // 精选id
  public static final byte MESSAGE_CHOICENESS = 1;
  // 关注
  public static final byte MESSAGE_IS_ATTENTION = 2;
  // 评论
  public static final byte MESSAGE_COMMENT = 3;
  // 回复
  public static final byte MESSAGE_REPLY = 4;
  // 点赞
  public static final byte MESSAGE_PRISE = 5;
  // 外部链接
  public static final byte MESSAGE_EXTERNAL_CONNECTION = 6;
  //
  public static final byte REMIND_TODAY_SPORTPLAN = 7;
  // 是否已经阅读消息
  /** 已经阅读 */
  public static final byte IS_READ = 1;
  /** 未阅读 */
  public static final byte NO_READ = 0;
}
