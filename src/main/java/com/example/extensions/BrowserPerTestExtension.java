package com.example.extensions;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import static com.codeborne.selenide.WebDriverRunner.*;

public class BrowserPerTestExtension implements AfterEachCallback {

    @Override
    public void afterEach(ExtensionContext context) throws Exception {
        closeWebDriver();
    }
}
