package com.whyzaa.vspringblog.constant;

public interface CommonConstant {

    /**
     * 删除标志 1 未删除 0
     */
    public static final Integer DEL_FLAG_1 = 1;


    /**
     * 登录用户令牌缓存KEY前缀
     */
    public static final int TOKEN_EXPIRE_TIME = 3600; //3600秒即是一小时

    public static final String PREFIX_USER_TOKEN = "PREFIX_USER_TOKEN_";


    /**
     * token的key
     */
    public static String ACCESS_TOKEN = "Access-Token";

    /**
     * 登录用户规则缓存
     */
    public static final String LOGIN_USER_RULES_CACHE = "loginUser_cacheRules";

    /**
     * 登录用户拥有角色缓存KEY前缀
     */
    public static String LOGIN_USER_CACHERULES_ROLE = "loginUser_cacheRules::Roles_";

    /**
     * 登录用户拥有权限缓存KEY前缀
     */
    public static String LOGIN_USER_CACHERULES_PERMISSION = "loginUser_cacheRules::Permissions_";



    public static final String ARTICLE_VIEWCOUNT_KEY = "article_view";

    /**点赞数量 key**/
    public static final String ARTICLE_APPROVECOUNT_KEY = "article_approve";
    /** 浏览数量每篇 的 key**/
    public static final String ARTICLE_VIEWCOUNT_CODE = "viewcount_";

    /**点赞数每篇的 key**/
    public static final String ARTICLE_APPROVECOUNT_CODE = "approvecount_";

}
