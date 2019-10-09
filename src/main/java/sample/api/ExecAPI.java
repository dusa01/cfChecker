package sample.api;

import com.fasterxml.jackson.core.type.TypeReference;

public abstract class ExecAPI<T1, T2> {

    /**
     * レスポンスタイプ
     */
    protected TypeReference<T1> type = null;

    /**
     * APIエンドポイント
     */
    protected String url;

    /**
     * HTTP経由でAPIを実行する
     * JSONパラメータがあればrequest bodyに展開する
     */
    public T1 execHttpApi() throws Exception {
        return new APIClient<T1, T2>().requestApi(this.type, createRequest(), this.url, APIClient.METHOD.POST);
    }

    /**
     * リクエスト情報を生成し取得します
     *
     * @return 生成したリクエスト情報
     */
    protected abstract T2 createRequest();
}
