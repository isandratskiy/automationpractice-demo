package pageobjects.fragments;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.AllArgsConstructor;
import pageobjects.pages.ProductDetailPage;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

@AllArgsConstructor
public class SearchFragment {
    private final SelenideElement container;

    public SearchFragment() {
        this.container = $("#searchbox");
    }

    private void searchProduct(String query) {
        this.container.$(".search_query").val(query);
    }

    @Step("search product by query '{query}' ")
    public ProductDetailPage getFirstProductFromSearch(String query) {
        this.searchProduct(query);
        $$(".ac_results > ul > li").first().hover().click();
        return new ProductDetailPage();
    }
}
