Feature: [821883][821884][821885][821886][821887] International Widgets

  Background: 
    Given ANZ user is logged in
@archived
  Scenario Outline: Widgets on country page
    When the user opens "<link>" url on PL AU website
    Then the following widgets should be displayed
      | Contributing firms include                |
      | Country Q&A comparison tool              |
      | Recently published                        |
      | Contribute to Practical Law International content |
      | Doing business in <country>?              |
	And the text on widget "Country Q&A comparison tool" is "Compare answers to key questions about doing a variety of transactions across multiple jurisdictions."

    Examples: 
      | link                                              | country                  |
      | /Browse/Home/International/Argentina              | ARGENTINA                |
      | /Browse/Home/International/Australia              | AUSTRALIA                |
#      | /Browse/Home/International/Bahrain                | BAHRAIN                  |
#      | /Browse/Home/International/Belgium                | BELGIUM                  |
#      | /Browse/Home/International/Brazil                 | BRAZIL                   |
#      | /Browse/Home/International/Bulgaria               | BULGARIA                 |
#      | /Browse/Home/International/Canada                 | CANADA                   |
#      | /Browse/Home/International/CaymanIslands          | CAYMAN ISLANDS           |
#      | /Browse/Home/International/Chile                  | CHILE                    |
#      | /Browse/Home/International/Colombia               | COLOMBIA                 |
#      | /Browse/Home/International/Cyprus                 | CYPRUS                   |
#      | /Browse/Home/International/CzechRepublic          | CZECH REPUBLIC           |
#      | /Browse/Home/International/Denmark                | DENMARK                  |
#      | /Browse/Home/International/Egypt                  | EGYPT                    |
#      | /Browse/Home/International/France                 | FRANCE                   |
#      | /Browse/Home/International/Germany                | GERMANY                  |
#      | /Browse/Home/International/Gibraltar              | GIBRALTAR                |
#      | /Browse/Home/International/HongKong               | HONG KONG                |
#      | /Browse/Home/International/Hungary                | HUNGARY                  |
#      | /Browse/Home/International/Iceland                | ICELAND                  |
#      | /Browse/Home/International/India                  | INDIA                    |
#      | /Browse/Home/International/Indonesia              | INDONESIA                |
#      | /Browse/Home/International/Ireland                | IRELAND                  |
#      | /Browse/Home/International/Israel                 | ISRAEL                   |
#      | /Browse/Home/International/Italy                  | ITALY                    |
#      | /Browse/Home/International/Luxembourg             | LUXEMBOURG               |
#      | /Browse/Home/International/Malaysia               | MALAYSIA                 |
#      | /Browse/Home/International/Mexico                 | MEXICO                   |
#      | /Browse/Home/International/NewZealand             | NEW ZEALAND              |
#      | /Browse/Home/International/Nigeria                | NIGERIA                  |
#      | /Browse/Home/International/Peru                   | PERU                     |
#      | /Browse/Home/International/Portugal               | PORTUGAL                 |
#      | /Browse/Home/International/Romania                | ROMANIA                  |
#      | /Browse/Home/International/RussianFederation      | RUSSIAN FEDERATION       |
#      | /Browse/Home/International/SaudiArabia            | SAUDI ARABIA             |
#      | /Browse/Home/International/Singapore              | SINGAPORE                |
#      | /Browse/Home/International/SouthAfrica            | SOUTH AFRICA             |
#      | /Browse/Home/International/SouthKorea             | SOUTH KOREA              |
#      | /Browse/Home/International/Spain                  | SPAIN                    |
#      | /Browse/Home/International/Sweden                 | SWEDEN                   |
#      | /Browse/Home/International/Switzerland            | SWITZERLAND              |
#      | /Browse/Home/International/Taiwan                 | TAIWAN                   |
#      | /Browse/Home/International/Thailand               | THAILAND                 |
#      | /Browse/Home/International/TheNetherlands         | THE NETHERLANDS          |
#      | /Browse/Home/International/Turkey                 | TURKEY                   |
#      | /Browse/Home/International/UnitedArabEmirates     | UNITED ARAB EMIRATES     |
#      | /Browse/Home/International/UnitedKingdom          | UNITED KINGDOM           |
#      | /Browse/Home/International/UnitedStates           | UNITED STATES            |
#      | /Browse/Home/International/Vietnam                | VIETNAM                  |
#	#
#	#		For the following countries there is no Doing Business widget: 
#      | /Browse/Home/International/Austria                | AUSTRIA                  |
#      | /Browse/Home/International/Bermuda                | BERMUDA                  |
#      | /Browse/Home/International/ChannelIslandsGuernsey | CHANNEL ISLANDS GUERNSEY |
#      | /Browse/Home/International/ChannelIslandsJersey   | CHANNEL ISLANDS JERSEY   |
#      | /Browse/Home/International/China                  | CHINA                    |
#      | /Browse/Home/International/Croatia                | CROATIA                  |
#      | /Browse/Home/International/Estonia                | ESTONIA                  |
#      | /Browse/Home/International/Finland                | FINLAND                  |
#      | /Browse/Home/International/Greece                 | GREECE                   |
#      | /Browse/Home/International/IsleofMan              | ISLE OF MAN              |
#      | /Browse/Home/International/Japan                  | JAPAN                    |
#      | /Browse/Home/International/Kazakhstan             | KAZAKHSTAN               |
#      | /Browse/Home/International/Norway                 | NORWAY                   |
#      | /Browse/Home/International/Oman                   | OMAN                     |
#      | /Browse/Home/International/Poland                 | POLAND                   |
#      | /Browse/Home/International/Qatar                  | QATAR                    |
#      | /Browse/Home/International/Serbia                 | SERBIA                   |
#      | /Browse/Home/International/Ukraine                | UKRAINE                  |
#      | /Browse/Home/International/Venezuela              | VENEZUELA                |

@archived
  Scenario: Widgets on International Transaction Guides page
    When the user opens "/Browse/Home/International/Internationaltransactionguides" url on PL AU website
    Then the following widgets should be displayed
      | Testimonials                              |
      | Contribute to Practical Law International content |

  @archived
  Scenario: Widgets on Global Guides page
    When the user opens "/Browse/Home/International/Globalguides" url on PL AU website
    Then the following widgets should be displayed
      | Contributing firms include                |
      | Country Q&A comparison tool               |
      | Contribute to Practical Law International content |

  Scenario: Widgets on Arbitration PA
    When the user opens "/Browse/Home/Practice/Arbitration" url on PL AU website
    Then the following widgets should be displayed
      | Legal updates                           |
      | Investment treaty arbitration resources |

  Scenario: Widgets on EU Law PA
    When the user opens "/Browse/Home/Practice/EULaw" url on PL AU website
    Then the following widgets should be displayed
      | Legal updates |

  Scenario: Widgets on Competition PA
    When the user opens "/Browse/Home/Practice/Competition" url on PL AU website
    Then the following widgets should be displayed
      | UK Legal updates |
      | EU Legal updates |

  @archived
  Scenario: [821884] Country Q&A comparison tool widget
    When the user opens "/Browse/Home/International/Canada" url on PL AU website
    And the user clicks on button "Start comparing" on widget "Country Q&A comparison tool"
    Then the user is presented with comparison tool page with header "Country Q&A Comparison Tool"

  #last two steps not working for unknown reason, to be investigated
  @pendingAutomation @archived
  Scenario: [821885] Recently Published widget - view all button
    When the user opens "/Browse/Home/International/Argentina" url on PL AU website
   	Then the user can see the button "View all" on widget "Recently published"
  #  And the user clicks on button "View all" on widget "Recently published"
  #  Then the user is presented with a page with header "Argentina: Recently published"

  @archived
  Scenario: [821886] Contribute to Global Guides widget
    When the user opens "/Browse/Home/International/RussianFederation" url on PL AU website
  	And the text on widget "Contribute to Practical Law International content" is "Showcase your expertise in Practical Law global guides and international transactions guides."
  	And the user clicks on button "Find out more" on widget "Contribute to Practical Law International content"
    Then the user is presented with a page with header "About global guides"


  #837690 [PLAU & PLUK] Browse -  "Doing business" widget for India, Indonesia, South Korea, South Africa countries' pages are showing "Argentina" page rather than their own pages
	@bug @archived
  Scenario Outline: [821887] Doing business in country widget
    When the user opens "<link>" url on PL AU website
    And the user clicks on button "Find out more" on widget "Doing business in <country>?"
    Then document title is displayed as "Doing business in <country2>"
      Examples: 
      | link                                              | country                  | country2  |
#      | /Browse/Home/International/Argentina              | ARGENTINA                | Argentina  |
      | /Browse/Home/International/Australia              | AUSTRALIA                | Australia  |
      | /Browse/Home/International/Bahrain                | BAHRAIN                  | Bahrain   |
#      | /Browse/Home/International/Belgium                | BELGIUM                  | Belgium   |
#      | /Browse/Home/International/Brazil                 | BRAZIL                   | Brazil   |
#      | /Browse/Home/International/Bulgaria               | BULGARIA                 | Bulgaria |
#      | /Browse/Home/International/Canada                 | CANADA                   | Canada   |
#      | /Browse/Home/International/CaymanIslands          | CAYMAN ISLANDS           | Cayman Islands |
#      | /Browse/Home/International/Chile                  | CHILE                    | Chile  |
#      | /Browse/Home/International/Colombia               | COLOMBIA                 | Colombia |
#      | /Browse/Home/International/Cyprus                 | CYPRUS                   | Cyprus  |
#      | /Browse/Home/International/CzechRepublic          | CZECH REPUBLIC           | Czech Republic |
#      | /Browse/Home/International/Denmark                | DENMARK                  | Denmark |
#      | /Browse/Home/International/Egypt                  | EGYPT                    | Egypt  |
#      | /Browse/Home/International/France                 | FRANCE                   | France |
#      | /Browse/Home/International/Germany                | GERMANY                  | Germany |
#      | /Browse/Home/International/Gibraltar              | GIBRALTAR                | Gibraltar |
#      | /Browse/Home/International/HongKong               | HONG KONG                | Hong Kong |
#      | /Browse/Home/International/Hungary                | HUNGARY                  | Hungary  |
#      | /Browse/Home/International/Iceland                | ICELAND                  | Iceland |
#      | /Browse/Home/International/India                  | INDIA                    | India  |
#      | /Browse/Home/International/Indonesia              | INDONESIA                | Indonesia |
#      | /Browse/Home/International/Ireland                | IRELAND                  | Ireland  |
#      | /Browse/Home/International/Israel                 | ISRAEL                   | Israel  |
#      | /Browse/Home/International/Italy                  | ITALY                    | Italy  |
#      | /Browse/Home/International/Luxembourg             | LUXEMBOURG               | Luxembourg  |
#      | /Browse/Home/International/Malaysia               | MALAYSIA                 | Malaysia |
#      | /Browse/Home/International/Mexico                 | MEXICO                   | Mexico  |
#      | /Browse/Home/International/NewZealand             | NEW ZEALAND              | New Zealand |
#      | /Browse/Home/International/Nigeria                | NIGERIA                  | Nigeria |
#      | /Browse/Home/International/Peru                   | PERU                     | Peru |
#      | /Browse/Home/International/Portugal               | PORTUGAL                 | Portugal |
#      | /Browse/Home/International/Romania                | ROMANIA                  | Romania |
#      | /Browse/Home/International/RussianFederation      | RUSSIAN FEDERATION       | Russian Federation |
#      | /Browse/Home/International/SaudiArabia            | SAUDI ARABIA             | Saudi Arabia |
#      | /Browse/Home/International/Singapore              | SINGAPORE                | Singapore |
#      | /Browse/Home/International/SouthAfrica            | SOUTH AFRICA             | South Africa | 
#      | /Browse/Home/International/SouthKorea             | SOUTH KOREA              | South Korea |
#      | /Browse/Home/International/Spain                  | SPAIN                    | Spain |
#      | /Browse/Home/International/Sweden                 | SWEDEN                   | Sweden |
#      | /Browse/Home/International/Switzerland            | SWITZERLAND              | Switzerland |
#      | /Browse/Home/International/Taiwan                 | TAIWAN                   | Taiwan |
#      | /Browse/Home/International/Thailand               | THAILAND                 | Thailand |
#      | /Browse/Home/International/TheNetherlands         | THE NETHERLANDS          | The Netherlands |
#      | /Browse/Home/International/Turkey                 | TURKEY                   | Turkey |
#      | /Browse/Home/International/UnitedArabEmirates     | UNITED ARAB EMIRATES     | United Arab Emirates |
#      | /Browse/Home/International/UnitedKingdom          | UNITED KINGDOM           | United Kingdom |
#      | /Browse/Home/International/UnitedStates           | UNITED STATES            | United States |
#      | /Browse/Home/International/Vietnam                | VIETNAM                  | Vietnam |
