/*
 * Copyright © 2018 DMMGAMES All rights reserved.
 */
package sample.api.impl;

/**
 * CloudFrontからターゲットを取得する際のURLを設定します。
 *
 * @author usanami-daisuke
 */
public class CloudFrontUrl {
    /**
     * ターゲットのURL
     */
    private String url;

    /**
     * コンストラクタ
     *
     * @param url ターゲットのURL
     */
    public CloudFrontUrl(String url) {
        this.url = url;
    }

    /**
     * ターゲットのURLを取得します。
     */
    public String getUrl() {
        return url;
    }

    /**
     * ターゲットのURLを設定します。
     *
     * @param url ターゲットのURL
     */
    public void setUrl(String url) {
        this.url = url;
    }
}
