package sample.api.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import sample.api.ExecAPI;

public class GetCloudFrontCookie<T1, T2> extends ExecAPI<T1, CloudFrontUrl> {
    private String cloudFrontPolicyUrl;

    public GetCloudFrontCookie(String url, TypeReference<T1> type, String policyUrl) {
        this.url = url;
        this.type = type;
        this.cloudFrontPolicyUrl = policyUrl;
    }

    @Override
    protected CloudFrontUrl createRequest() {
        return new CloudFrontUrl(cloudFrontPolicyUrl);
    }
}
