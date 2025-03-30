package helpers;

import lombok.Getter;

@Getter
public enum EndPoint {
    HOME(PropertyProvider.getInstance().getProperty("web.url")),
    ADDCUST(PropertyProvider.getInstance().getProperty("web.url").concat("/addCust")),
    CASTLIST(PropertyProvider.getInstance().getProperty("web.url").concat("/list"));

    private final String url;

    EndPoint(String url) {this.url = url;}
}
