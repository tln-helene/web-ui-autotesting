Feature: Добавить товар в корзину с параметрами

  Background:
    Given Пользователь зашел на сайт

    @hooks
      @close
    Scenario Outline:
      When Пользователь логинится на сайте
      And                Наводим кнопку мыши на Women
      And Кликаем на кнопку T-Shirts
      And Выбираем размер '<size>'
      And Наводим курсор мыши на карточку товара и добавляем в корзину
      Then Проверяем итоговую сумму
      Examples:
        | size |
        | S |
        | M |

