package com.yun.authority.core.auth;

import com.yun.authority.core.function.AuthFunction;
import com.yun.authority.core.session.AuthSession;

import java.util.List;

/**
 * <p>
 *     Token 权限验证工具类
 * </p>
 *
 * @author sunkaiyun
 * @version 1.0
 * @date 2021-08-09 15:50
 */
public class AuthUtil {

    /**
     * 账号类型标识
     */
    public static final String TYPE = "login";

    /**
     * 底层的 AuthLogic 对象
     */
    public static AuthLogic authLogic = new AuthLogic(TYPE);

    /**
     * 获取当前 AuthLogic 的账号类型
     * @return See Note
     */
    public static String getLoginType(){
        return authLogic.getLoginType();
    }

    // =================== 获取token 相关 ===================

    /**
     * 返回token名称 
     * @return 此authLogic的token名称
     */
    public static String getTokenName() {
        return authLogic.getTokenName();
    }

    /**
     * 在当前会话写入当前TokenValue 
     * @param tokenValue token值 
     * @param cookieTimeout Cookie存活时间(秒)
     */
    public static void setTokenValue(String tokenValue, int cookieTimeout){
        authLogic.setTokenValue(tokenValue, cookieTimeout);
    }

    /**
     * 获取当前TokenValue
     * @return 当前tokenValue
     */
    public static String getTokenValue() {
        return authLogic.getTokenValue();
    }

    /**
     * 获取当前会话的Token信息 
     * @return token信息
     */
    public static AuthTokenInfo getTokenInfo() {
        return authLogic.getTokenInfo();
    }


    // =================== 登录相关操作 ===================

    /**
     * 会话登录 
     * @param id 账号id，建议的类型：（long | int | String）
     */
    public static void login(Object id) {
        authLogic.login(id);
    }

    /**
     * 会话登录，并指定登录设备 
     * @param id 账号id，建议的类型：（long | int | String）
     * @param device 设备标识 
     */
    public static void login(Object id, String device) {
        authLogic.login(id, device);
    }

    /**
     * 会话登录，并指定是否 [记住我] 
     * @param id 账号id，建议的类型：（long | int | String）
     * @param isLastingCookie 是否为持久Cookie 
     */
    public static void login(Object id, boolean isLastingCookie) {
        authLogic.login(id, isLastingCookie);
    }

    /**
     * 会话登录，并指定所有登录参数Model 
     * @param id 登录id，建议的类型：（long | int | String）
     * @param loginModel 此次登录的参数Model 
     */
    public static void login(Object id, AuthLoginModel loginModel) {
        authLogic.login(id, loginModel);
    }

    /**
     * 会话注销
     */
    public static void logout() {
        authLogic.logout();
    }

    /**
     * 会话注销，根据指定Token
     * @param tokenValue 指定token
     */
    public static void logoutByTokenValue(String tokenValue) {
        authLogic.logoutByTokenValue(tokenValue);
    }

    /**
     * 会话注销，根据账号id （踢人下线）
     * <p> 当对方再次访问系统时，会抛出NotLoginException异常，场景值=-2 </p>
     * @param loginId 账号id 
     */
    public static void logoutByLoginId(Object loginId) {
        authLogic.logoutByLoginId(loginId);
    }

    /**
     * 会话注销，根据账号id and 设备标识 （踢人下线）
     * <p> 当对方再次访问系统时，会抛出NotLoginException异常，场景值=-2
     * @param loginId 账号id 
     * @param device 设备标识 
     */
    public static void logoutByLoginId(Object loginId, String device) {
        authLogic.logoutByLoginId(loginId, device);
    }

    // 查询相关

    /**
     * 当前会话是否已经登录 
     * @return 是否已登录
     */
    public static boolean isLogin() {
        return authLogic.isLogin();
    }

    /**
     * 检验当前会话是否已经登录，如未登录，则抛出异常 
     */
    public static void checkLogin() {
        authLogic.checkLogin();
    }

    /**
     * 获取当前会话账号id, 如果未登录，则抛出异常 
     * @return 账号id
     */
    public static Object getLoginId() {
        return authLogic.getLoginId();
    }

    /**
     * 获取当前会话账号id, 如果未登录，则返回默认值 
     * @param <T> 返回类型 
     * @param defaultValue 默认值
     * @return 登录id
     */
    public static <T> T getLoginId(T defaultValue) {
        return authLogic.getLoginId(defaultValue);
    }

    /**
     * 获取当前会话账号id, 如果未登录，则返回null 
     * @return 账号id
     */
    public static Object getLoginIdDefaultNull() {
        return authLogic.getLoginIdDefaultNull();
    }

    /**
     * 获取当前会话账号id, 并转换为String类型
     * @return 账号id
     */
    public static String getLoginIdAsString() {
        return authLogic.getLoginIdAsString();
    }

    /**
     * 获取当前会话账号id, 并转换为int类型
     * @return 账号id
     */
    public static int getLoginIdAsInt() {
        return authLogic.getLoginIdAsInt();
    }

    /**
     * 获取当前会话账号id, 并转换为long类型 
     * @return 账号id
     */
    public static long getLoginIdAsLong() {
        return authLogic.getLoginIdAsLong();
    }

    /**
     * 获取指定Token对应的账号id，如果未登录，则返回 null 
     * @param tokenValue token
     * @return 账号id
     */
    public static Object getLoginIdByToken(String tokenValue) {
        return authLogic.getLoginIdByToken(tokenValue);
    }


    // =================== session相关 ===================

    /**
     * 获取指定账号id的Session, 如果Session尚未创建，isCreate=是否新建并返回
     * @param loginId 账号id
     * @param isCreate 是否新建
     * @return Session对象
     */
    public static AuthSession getSessionByLoginId(Object loginId, boolean isCreate) {
        return authLogic.getSessionByLoginId(loginId, isCreate);
    }

    /**
     * 获取指定key的Session, 如果Session尚未创建，则返回null
     * @param sessionId SessionId
     * @return Session对象
     */
    public static AuthSession getSessionBySessionId(String sessionId) {
        return authLogic.getSessionBySessionId(sessionId);
    }

    /**
     * 获取指定账号id的Session，如果Session尚未创建，则新建并返回 
     * @param loginId 账号id 
     * @return Session对象
     */
    public static AuthSession getSessionByLoginId(Object loginId) {
        return authLogic.getSessionByLoginId(loginId);
    }

    /**
     * 获取当前会话的Session, 如果Session尚未创建，isCreate=是否新建并返回 
     * @param isCreate 是否新建 
     * @return Session对象
     */
    public static AuthSession getSession(boolean isCreate) {
        return authLogic.getSession(isCreate);
    }

    /**
     * 获取当前会话的Session，如果Session尚未创建，则新建并返回 
     * @return Session对象
     */
    public static AuthSession getSession() {
        return authLogic.getSession();
    }


    // =================== token专属session ===================  

    /**
     * 获取指定Token-Session，如果Session尚未创建，则新建并返回 
     * @param tokenValue Token值
     * @return Session对象
     */
    public static AuthSession getTokenSessionByToken(String tokenValue) {
        return authLogic.getTokenSessionByToken(tokenValue);
    }

    /**
     * 获取当前Token-Session，如果Session尚未创建，则新建并返回
     * @return Session对象
     */
    public static AuthSession getTokenSession() {
        return authLogic.getTokenSession();
    }


    // =================== [临时过期] 验证相关 ===================  

    /**
     * 检查当前token 是否已经[临时过期]，如果已经过期则抛出异常  
     */
    public static void checkActivityTimeout() {
        authLogic.checkActivityTimeout();
    }

    /**
     * 续签当前token：(将 [最后操作时间] 更新为当前时间戳) 
     * <h1>请注意: 即时token已经 [临时过期] 也可续签成功，
     * 如果此场景下需要提示续签失败，可在此之前调用 checkActivityTimeout() 强制检查是否过期即可 </h1>
     */
    public static void updateLastActivityToNow() {
        authLogic.updateLastActivityToNow();
    }


    // =================== 过期时间相关 ===================  

    /**
     * 获取当前登录者的token剩余有效时间 (单位: 秒)
     * @return token剩余有效时间
     */
    public static long getTokenTimeout() {
        return authLogic.getTokenTimeout();
    }

    /**
     * 获取当前登录者的Session剩余有效时间 (单位: 秒)
     * @return token剩余有效时间
     */
    public static long getSessionTimeout() {
        return authLogic.getSessionTimeout();
    }

    /**
     * 获取当前token的专属Session剩余有效时间 (单位: 秒) 
     * @return token剩余有效时间
     */
    public static long getTokenSessionTimeout() {
        return authLogic.getTokenSessionTimeout();
    }

    /**
     * 获取当前token[临时过期]剩余有效时间 (单位: 秒)
     * @return token[临时过期]剩余有效时间
     */
    public static long getTokenActivityTimeout() {
        return authLogic.getTokenActivityTimeout();
    }



    // =================== 角色验证操作 ===================  

    /**
     * 指定账号id是否含有角色标识, 返回true或false 
     * @param loginId 账号id
     * @param role 角色标识
     * @return 是否含有指定角色标识
     */
    public static boolean hasRole(Object loginId, String role) {
        return authLogic.hasRole(loginId, role);
    }

    /**
     * 当前账号是否含有指定角色标识, 返回true或false 
     * @param role 角色标识
     * @return 是否含有指定角色标识
     */
    public static boolean hasRole(String role) {
        return authLogic.hasRole(role);
    }

    /**
     * 当前账号是否含有指定角色标识, 如果验证未通过，则抛出异常: NotRoleException 
     * @param role 角色标识
     */
    public static void checkRole(String role) {
        authLogic.checkRole(role);
    }

    /**
     * 当前账号是否含有指定角色标识 [指定多个，必须全部验证通过] 
     * @param roleArray 角色标识数组
     */
    public static void checkRoleAnd(String... roleArray){
        authLogic.checkRoleAnd(roleArray);
    }

    /**
     * 当前账号是否含有指定角色标识 [指定多个，只要其一验证通过即可] 
     * @param roleArray 角色标识数组
     */
    public static void checkRoleOr(String... roleArray){
        authLogic.checkRoleOr(roleArray);
    }


    // =================== 权限验证操作 ===================

    /**
     * 指定账号id是否含有指定权限, 返回true或false 
     * @param loginId 账号id
     * @param permission 权限码
     * @return 是否含有指定权限
     */
    public static boolean hasPermission(Object loginId, String permission) {
        return authLogic.hasPermission(loginId, permission);
    }

    /**
     * 当前账号是否含有指定权限, 返回true或false 
     * @param permission 权限码
     * @return 是否含有指定权限
     */
    public static boolean hasPermission(String permission) {
        return authLogic.hasPermission(permission);
    }

    /**
     * 当前账号是否含有指定权限, 如果验证未通过，则抛出异常: NotPermissionException 
     * @param permission 权限码
     */
    public static void checkPermission(String permission) {
        authLogic.checkPermission(permission);
    }

    /**
     * 当前账号是否含有指定权限 [指定多个，必须全部验证通过] 
     * @param permissionArray 权限码数组
     */
    public static void checkPermissionAnd(String... permissionArray) {
        authLogic.checkPermissionAnd(permissionArray);
    }

    /**
     * 当前账号是否含有指定权限 [指定多个，只要其一验证通过即可] 
     * @param permissionArray 权限码数组
     */
    public static void checkPermissionOr(String... permissionArray) {
        authLogic.checkPermissionOr(permissionArray);
    }


    // =================== id 反查token 相关操作 ===================  

    /**
     * 获取指定账号id的tokenValue 
     * <p> 在配置为允许并发登录时，此方法只会返回队列的最后一个token，
     * 如果你需要返回此账号id的所有token，请调用 getTokenValueListByLoginId 
     * @param loginId 账号id
     * @return token值
     */
    public static String getTokenValueByLoginId(Object loginId) {
        return authLogic.getTokenValueByLoginId(loginId);
    }

    /**
     * 获取指定账号id指定设备端的tokenValue 
     * <p> 在配置为允许并发登录时，此方法只会返回队列的最后一个token，
     * 如果你需要返回此账号id的所有token，请调用 getTokenValueListByLoginId 
     * @param loginId 账号id
     * @param device 设备标识 
     * @return token值
     */
    public static String getTokenValueByLoginId(Object loginId, String device) {
        return authLogic.getTokenValueByLoginId(loginId, device);
    }

    /**
     * 获取指定账号id的tokenValue集合 
     * @param loginId 账号id 
     * @return 此loginId的所有相关token
     */
    public static List<String> getTokenValueListByLoginId(Object loginId) {
        return authLogic.getTokenValueListByLoginId(loginId);
    }

    /**
     * 获取指定账号id指定设备端的tokenValue 集合 
     * @param loginId 账号id 
     * @param device 设备标识 
     * @return 此loginId的所有相关token
     */
    public static List<String> getTokenValueListByLoginId(Object loginId, String device) {
        return authLogic.getTokenValueListByLoginId(loginId, device);
    }

    /**
     * 返回当前会话的登录设备 
     * @return 当前令牌的登录设备
     */
    public static String getLoginDevice() {
        return authLogic.getLoginDevice();
    }


    // =================== 会话管理 ===================  

    /**
     * 根据条件查询Token 
     * @param keyword 关键字 
     * @param start 开始处索引 (-1代表查询所有) 
     * @param size 获取数量 
     * @return token集合
     */
    public static List<String> searchTokenValue(String keyword, int start, int size) {
        return authLogic.searchTokenValue(keyword, start, size);
    }

    /**
     * 根据条件查询SessionId 
     * @param keyword 关键字 
     * @param start 开始处索引 (-1代表查询所有) 
     * @param size 获取数量 
     * @return sessionId集合
     */
    public static List<String> searchSessionId(String keyword, int start, int size) {
        return authLogic.searchSessionId(keyword, start, size);
    }

    /**
     * 根据条件查询Token专属Session的Id 
     * @param keyword 关键字 
     * @param start 开始处索引 (-1代表查询所有) 
     * @param size 获取数量 
     * @return sessionId集合
     */
    public static List<String> searchTokenSessionId(String keyword, int start, int size) {
        return authLogic.searchTokenSessionId(keyword, start, size);
    }


    // ------------------- 账号封禁 -------------------  

    /**
     * 封禁指定账号
     * <p> 此方法不会直接将此账号id踢下线，而是在对方再次登录时抛出`DisableLoginException`异常 
     * @param loginId 指定账号id 
     * @param disableTime 封禁时间, 单位: 秒 （-1=永久封禁）
     */
    public static void disable(Object loginId, long disableTime) {
        authLogic.disable(loginId, disableTime);
    }

    /**
     * 指定账号是否已被封禁 (true=已被封禁, false=未被封禁) 
     * @param loginId 账号id
     * @return see note
     */
    public static boolean isDisable(Object loginId) {
        return authLogic.isDisable(loginId);
    }

    /**
     * 获取指定账号剩余封禁时间，单位：秒（-1=永久封禁，-2=未被封禁）
     * @param loginId 账号id
     * @return see note 
     */
    public static long getDisableTime(Object loginId) {
        return authLogic.getDisableTime(loginId);
    }

    /**
     * 解封指定账号
     * @param loginId 账号id 
     */
    public static void untieDisable(Object loginId) {
        authLogic.untieDisable(loginId);
    }


    // =================== 身份切换 ===================  

    /**
     * 临时切换身份为指定账号id 
     * @param loginId 指定loginId 
     */
    public static void switchTo(Object loginId) {
        authLogic.switchTo(loginId);
    }

    /**
     * 结束临时切换身份
     */
    public static void endSwitch() {
        authLogic.endSwitch();
    }

    /**
     * 当前是否正处于[身份临时切换]中 
     * @return 是否正处于[身份临时切换]中
     */
    public static boolean isSwitch() {
        return authLogic.isSwitch();
    }

    /**
     * 在一个代码段里方法内，临时切换身份为指定账号id
     * @param loginId 指定账号id 
     * @param function 要执行的方法 
     */
    public static void switchTo(Object loginId, AuthFunction function) {
        authLogic.switchTo(loginId, function);
    }


    // ------------------- 二级认证 -------------------  

    /**
     * 在当前会话 开启二级认证 
     * @param safeTime 维持时间 (单位: 秒) 
     */
    public static void openSafe(long safeTime) {
        authLogic.openSafe(safeTime);
    }

    /**
     * 当前会话 是否处于二级认证时间内 
     * @return true=二级认证已通过, false=尚未进行二级认证或认证已超时 
     */
    public static boolean isSafe() {
        return authLogic.isSafe();
    }

    /**
     * 检查当前会话是否已通过二级认证，如未通过则抛出异常 
     */
    public static void checkSafe() {
        authLogic.checkSafe();
    }

    /**
     * 获取当前会话的二级认证剩余有效时间 (单位: 秒, 返回-2代表尚未通过二级认证)
     * @return 剩余有效时间
     */
    public static long getSafeTime() {
        return authLogic.getSafeTime();
    }

    /**
     * 在当前会话 结束二级认证 
     */
    public static void closeSafe() {
        authLogic.closeSafe();
    }

}
