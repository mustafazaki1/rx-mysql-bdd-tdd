Feature: url_download

  Scenario Outline: url_is_invalid
    Given Downloader
    When enters an invalid <url>
    Then print error
    Examples:
      | url             |
      | "Mustafa Zaki"  |
      | "khaled kee"    |
      | "Yusufe Hussin" |
      | "1243436564"    |
      | "dfgkfpgd"      |

  Scenario Outline: url is valid
    Given Downloader
    When enters an valid <url>
    Then download
    Examples:
      | url                                                                                                      |
      | "http://stackoverflow.com/questions/163360/regular-expression-to-match-urls-in-java"                     |
      | "https://docs.google.com/spreadsheets/d/1Cc-gCUqx4wWHepvNBRmrgydNrCwZ085GQp-IrxauXnA/edit#gid=823786479" |
      | "https://outlook.office.com/owa/?realm=cis.asu.edu.eg&exsvurl=1&ll-cc=1033&modurl=0"                     |
      | "https://github.com/mustafazaki1/rx-mysql-bdd-tdd                                                        |
