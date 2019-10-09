
package sample.api;

/**
 * ラムダのAPIに失敗した場合に発生する例外を提供します
 *
 * @author chiku-makoto / takabayashi-ko
 */
public class AWSLambdaApiException extends RuntimeException {
    /**
     * シリアルバージョンUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * コンストラクタ<br />
     * APIのステータスコードは200で返却されてきます
     *
     * @param errorResponse エラーレスポンス情報
     */
    public AWSLambdaApiException(ResponseError errorResponse) {
        super(errorResponse.getErrorMessage());
    }
}