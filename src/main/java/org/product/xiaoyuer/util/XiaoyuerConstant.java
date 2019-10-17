package org.product.xiaoyuer.util;

public interface XiaoyuerConstant {
    /**
     * 激活成功
     */
    int ACTIVATION_SUCCESS = 0;
    /**
     *重复激活
     */
    int ACTIVATION_REPEAT = 1;
    /**
     * 激活失败
     */
    int ACTIVATION_FAILURE = 2;

    /**
     * 默认状态的登陆超市时间
     */

    int DEFAULT_EXPIRED_SECONDS = 3600 * 12;
    /**
     * 记住状态的登陆凭着时间
     */
    int REMEMBER_EXPIRED_SECONDS = 3600 * 24 * 100;
}
