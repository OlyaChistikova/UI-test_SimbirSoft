package helpers;

import lombok.Getter;

/**
 * Перечисление конечных точек (URL) приложения.
 * Каждая конечная точка соответствует определенному ресурсу в веб-приложении.
 */
@Getter
public enum EndPoint {
    HOME(PropertyProvider.getProperty("web.url")),
    ADDCUST(PropertyProvider.getProperty("web.url").concat("/addCust")),
    CASTLIST(PropertyProvider.getProperty("web.url").concat("/list"));

    private final String url;

    /**
     * Конструктор перечисления, который инициализирует URL для каждой конечной точки.
     *
     * @param url строка, представляющая URL конечной точки.
     */
    EndPoint(String url) {this.url = url;}
}
