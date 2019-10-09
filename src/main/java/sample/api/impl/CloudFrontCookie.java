/*
 * Copyright © 2018 DMMGAMES All rights reserved.
 */
package sample.api.impl;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * CloudFront用のCookie情報の構造を提供します。
 *
 * @author usanami-daisuke
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CloudFrontCookie {
    private String policy;
    private String signature;
    private String key;

    /**
     * CloudFrontポリシーを取得します。
     *
     * @return CloudFrontポリシー
     */
    public String getPolicy() {
        return policy;
    }

    /**
     * CloudFrontポリシーを設定します。
     *
     * @param policy CloudFrontポリシー
     */
    public void setPolicy(String policy) {
        this.policy = policy;
    }

    /**
     * CloudFront署名を取得します。
     *
     * @return CloudFront署名
     */
    public String getSignature() {
        return signature;
    }

    /**
     * CloudFront署名を設定します。
     *
     * @param signature CloudFront署名
     */
    public void setSignature(String signature) {
        this.signature = signature;
    }

    /**
     * CloudFrontキーペアを取得します。
     *
     * @return CloudFrontキーペア
     */
    public String getKey() {
        return key;
    }

    /**
     * CloudFrontキーペアを設定します。
     *
     * @param key CloudFrontキーペア
     */
    public void setKey(String key) {
        this.key = key;
    }

}
