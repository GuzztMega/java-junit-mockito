package br.com.guzzmega.financial.suite;

import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;

@Suite
@SuiteDisplayName("Tests Suite")
@SelectPackages(value = {
        "br.com.guzzmega.financial.domain",
        "br.com.guzzmega.financial.service"
})
public class SuiteTest  {
}
