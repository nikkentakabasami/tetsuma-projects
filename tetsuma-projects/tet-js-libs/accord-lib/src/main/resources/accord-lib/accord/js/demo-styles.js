/**
 * Функционал, использующийся для создания демок, тестирующих js.
 * 
 * Методы для показа css-стилей.
 * 
 */




let showCssStylesDefaultOptions = {
    showInContent: false,
    showInPrevSibling: false
}


/**
 * Показывает style элементов selector в заданном месте:
 * showInContent - показывать в самом элементе.
 * showInPrevSibling - показывать в предыдущем элементе.
 * 
 * 
 * Пример: показывать стиль элементов с классом wrapper в h3, перед ними.
 * showCssStylesForElements(".wrapper",{
 * 	showInContent: false,
 * 	showInPrevSibling: true
 * });
 * 
 * <h3>-</h3>
 * <div class="wrapper" style="display: grid;grid-template-columns: 200px auto 20%;">
 * 	<div>One</div>
 * </div>
 * 
 */
function showCssStylesForElements(selector, opt) {

    let options = $.extend({}, showCssStylesDefaultOptions, opt);


    $(selector).each((index, el) => {
        let $el = $(el);
        let styleText = $(el).attr("style");
        if (!styleText) {
            return;
        }
        let infoPanelSelector = $el.data("show-style-in");
        if (infoPanelSelector) {
            $("#" + infoPanelSelector).text(styleText);
        }
        if (options.showInContent) {
            $el.text(styleText);
        }
        if (options.showInPrevSibling) {
            $el.prevAll(":header:first").text(styleText);
        }
    });

}


/**
 * Показывает в заданном заголовке содержимое блока <element>.
 * 
 * Пример использования:
 * <style id="style1">...
 * 
 * <h4 data-style-element="style1">-</h4>
 * 
 */
function showStyleTagText() {


    $(":header[data-style-element]").each((index, el) => {
        let $el = $(el);
        let styleElement = $(el).data("style-element");

        let styleText = $("#" + styleElement).text();

        $el.text(styleText)
    });

}

