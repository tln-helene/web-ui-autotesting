Feature: Открытая часть сайта WB

  Background:
    Given Пользователь зашел на сайт WB

  @hooks
  @close
  Scenario: Нажатие кнопки [Получить код] при пустом номере телефона на форме входа
    When Нажать кнопку Войти
    And Ввести пустой номер телефона
    And Нажать кнопку Получить код
    Then Проверить текст сообщения об ошибке

#    new MainPageWB(driver).clickLoginButton()
#    .enterPhone("")
#    .clickGetCodeButton()
#    .checkLoginErrorMessage("Введите номер телефона!");


  @hooks
    @close
  Scenario Outline: Открытие секции каталога
    When Нажать кнопку гамбургер
    And Кликнуть заданный раздел '<section>' в каталоге
    Then Проверить совпадение url открывшейся страницы с '<link>'
    Examples:
      | section  | link                                            |
      | Женщинам | https://www.wildberries.ru/catalog/zhenshchinam |
      | Мужчинам | https://www.wildberries.ru/catalog/muzhchinam   |
      | Детям    | https://www.wildberries.ru/catalog/detyam"      |

#    new MainPageWB(driver).clickHamburgerButton()
#    .clickCatalogItemLink(sCatalogSection)
#    .checkOpenedLink(sLink);


  @hooks
    @close
  Scenario Outline: Поиск товара по артикулу
    When Ввести в строку поиска артикул товара '<article>'
    And Кликнуть кнопку поиска
    Then Проверить артикул '<article>' в открывшейся карточке товара
    Examples:
      | article   |
      | 123456    |
      | 4567890   |
      | 111222333 |

#    new MainPageWB(driver).searchBlock.enterSearchString(sArticle)
#    .clickSearch()
#    .checkArticle(sArticle);
