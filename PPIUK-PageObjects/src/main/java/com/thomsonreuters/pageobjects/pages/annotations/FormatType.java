package com.thomsonreuters.pageobjects.pages.annotations;

import org.openqa.selenium.By;

/**
 * Created by UC186961 on 07/09/2015.
 */
public enum FormatType {

    // Direct Links present on TinyMCE
    BOLD(By.cssSelector(".mce-ico.mce-i-bold"), By.cssSelector(".co_richEditor.co_noteArea.mce-content-body strong"), "//strong[text()='%s']"),
    ITALIC(By.cssSelector(".mce-ico.mce-i-italic"), By.cssSelector(".co_richEditor.co_noteArea.mce-content-body em"), "//em[text()='%s']"),
    UNDERLINED(By.cssSelector(".mce-ico.mce-i-underline"), By.cssSelector(".co_richEditor.co_noteArea.mce-content-body span[data-mce-style*='underline']"), "//span[contains(@data-mce-style,'underline')][text()='%s']"),
    STRIKETHROUGH(By.cssSelector(".mce-ico.mce-i-strikethrough"), By.cssSelector(".co_richEditor.co_noteArea.mce-content-body span[data-mce-style*='line-through']"), "//span[contains(@data-mce-style,'line-through')][text()='%s']"),
    BULLET(By.cssSelector(".mce-ico.mce-i-bullist"), By.cssSelector(".co_richEditor.co_noteArea.mce-content-body ul li"), "//ul/li[text()='%s']"),
    NUMBER(By.cssSelector(".mce-ico.mce-i-numlist"), By.cssSelector(".co_richEditor.co_noteArea.mce-content-body ol li"), "//ol/li[text()='%s']"),
    INSERT_EDIT_LINK(By.cssSelector(".mce-ico.mce-i-link"), By.cssSelector(".co_richEditor.co_noteArea.mce-content-body a[href='%s']"), ""),
    UNLINK(By.cssSelector(".mce-ico.mce-i-unlink"), By.cssSelector(".co_richEditor.co_noteArea.mce-content-body p[text()='%s']"), ""),
    ALIGN_LEFT(By.cssSelector(".mce-ico.mce-i-alignleft"), By.xpath(".//div[@class='co_richEditor co_noteArea mce-content-body']//p[contains(@style,'left')]"), "//p[contains(@style,'left')][text()='%s']"),
    ALIGN_CENTER(By.cssSelector(".mce-ico.mce-i-aligncenter"), By.xpath(".//div[@class='co_richEditor co_noteArea mce-content-body']//p[contains(@style,'center')]"),"//p[contains(@style,'center')][text()='%s']"),
    ALIGN_RIGHT(By.cssSelector(".mce-ico.mce-i-alignright"), By.xpath(".//div[@class='co_richEditor co_noteArea mce-content-body']//p[contains(@style,'right')]"),"//p[contains(@style,'right')][text()='%s']"),

    UNDO(By.cssSelector(".mce-ico.mce-i-undo"), By.cssSelector(""), ""),
    REDO(By.cssSelector(".mce-ico.mce-i-redo"), By.cssSelector(""), ""),

    //Headings Menu Items
    HEADERS_HEADER1(By.xpath("//span[text()='Header 1']"), By.cssSelector(".co_richEditor.co_noteArea.mce-content-body h1"),"//h1[text()='%s']"),
    HEADERS_HEADER2(By.xpath("//span[text()='Header 2']"), By.cssSelector(".co_richEditor.co_noteArea.mce-content-body h2"),"//h2[text()='%s']"),
    HEADERS_HEADER3(By.xpath("//span[text()='Header 3']"), By.cssSelector(".co_richEditor.co_noteArea.mce-content-body h3"),"//h3[text()='%s']"),
    HEADERS_HEADER4(By.xpath("//span[text()='Header 4']"), By.cssSelector(".co_richEditor.co_noteArea.mce-content-body h4"),"//h4[text()='%s']"),
    HEADERS_HEADER5(By.xpath("//span[text()='Header 5']"), By.cssSelector(".co_richEditor.co_noteArea.mce-content-body h5"),"//h5[text()='%s']"),
    HEADERS_HEADER6(By.xpath("//span[text()='Header 6']"), By.cssSelector(".co_richEditor.co_noteArea.mce-content-body h6"),"//h6[text()='%s']"),

    //Inline Menu Items
    INLINE_BOLD(By.xpath("//span[text()='Bold']"), By.cssSelector(".co_richEditor.co_noteArea.mce-content-body strong"),"//strong[text()='%s']"),
    INLINE_ITALIC(By.xpath("//span[text()='Italic']"), By.cssSelector(".co_richEditor.co_noteArea.mce-content-body em"),"//em[text()='%s']"),
    INLINE_UNDERLINE(By.xpath("//span[text()='Underline']"), By.cssSelector(".co_richEditor.co_noteArea.mce-content-body span[data-mce-style*='underline']"),"//span[contains(@data-mce-style,'underline')][text()='%s']"),
    INLINE_STRIKETHROUGH(By.xpath("//span[text()='Strikethrough']"), By.cssSelector(".co_richEditor.co_noteArea.mce-content-body span[data-mce-style*='line-through']"),"//span[contains(@data-mce-style,'line-through')][text()='%s']"),
    INLINE_SUPERSCRIPT(By.xpath("//span[text()='Superscript']"), By.cssSelector(".co_richEditor.co_noteArea.mce-content-body sup"),"//sup[text()='%s']"),
    INLINE_SUBSCRIPT(By.xpath("//span[text()='Subscript']"), By.cssSelector(".co_richEditor.co_noteArea.mce-content-body sub"),"//sub[text()='%s']"),
    INLINE_CODE(By.xpath("//span[text()='Code']"), By.cssSelector(".co_richEditor.co_noteArea.mce-content-body code"),"//code[text()='%s']"),

    //Blocks Menu Items
    BLOCKS_PARAGRAPH(By.xpath("//span[text()='Paragraph']"), By.cssSelector(".co_richEditor.co_noteArea.mce-content-body p"),"//p[text()='%s']"),
    BLOCKS_BLOCKQUOTE(By.xpath("//span[text()='Blockquote']"), By.cssSelector(".co_richEditor.co_noteArea.mce-content-body blockquote"),"//blockquote/p[text()='%s']"),
    BLOCKS_DIV(By.xpath("//span[text()='Div']"), By.cssSelector(".co_richEditor.co_noteArea.mce-content-body div"),"//div[text()='%s']"),
    BLOCKS_PRE(By.xpath("//span[text()='Pre']"), By.cssSelector(".co_richEditor.co_noteArea.mce-content-body pre"),"//pre[text()='%s']"),

    //Alignment Menu Items
    ALIGNMENT_LEFT(By.xpath("//span[text()='Left']"), By.cssSelector(".co_richEditor.co_noteArea.mce-content-body p[data-mce-style='text-align: left;']"),"//p[@data-mce-style='text-align: left;'][text()='%s']"),
    ALIGNMENT_RIGHT(By.xpath("//span[text()='Right']"), By.cssSelector(".co_richEditor.co_noteArea.mce-content-body p[data-mce-style='text-align: right;']"),"//p[@data-mce-style='text-align: right;'][text()='%s']"),
    ALIGNMENT_CENTER(By.xpath("//span[text()='Center']"), By.cssSelector(".co_richEditor.co_noteArea.mce-content-body p[data-mce-style='text-align: center;']"),"//p[@data-mce-style='text-align: center;'][text()='%s']"),
    ALIGNMENT_JUSTIFY(By.xpath("//span[text()='Justify']"), By.cssSelector(".co_richEditor.co_noteArea.mce-content-body p[data-mce-style='text-align: justify;']"),"//p[@data-mce-style='text-align: justify;'][text()='%s']");

    private By locator;
    private By cssOfText;
    private String savedTextCSS;

    FormatType(By by, By cssOfText, String savedTextCSS) {
        this.locator = by;
        this.cssOfText = cssOfText;
        this.savedTextCSS = savedTextCSS;
    }

    public By getStyleLocator() {
        return this.locator;
    }
    public By getCssOfText() {
        return this.cssOfText;
    }
    public String getSavedTextCSS() {
        return this.savedTextCSS;
    }

    public static FormatType getFormatType(String style) {
        return FormatType.valueOf(style.toUpperCase().trim());
    }
}
