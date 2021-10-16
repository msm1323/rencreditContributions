# language: ru

@all
Функционал: Рассчет вклада

  Структура сценария: Проверка расчетов по вкладу с валютой "<currency>"
    * Перейти по ссылке - https://rencredit.ru и перейти в меню – Вклады
    Тогда Выбрать подменю – Калькулятор доходности
    И Выбрать – <currency>
    И Выбрать открытие вклада - В отделении банка
    И Сумма вклада – <depAmount>
    И Срок – <term> месяцев
    И Ежемесячное пополнение – <mTopUp>
    И Отметить – Ежемесячная капитализация
    Затем Проверить расчеты по вкладу:
      | Начислено %                   | <percent>       |
      | Пополнение за <term> месяцев  | <replenishment> |
      | К снятию через <term> месяцев | <withdraw>      |

    Примеры:
      | currency    | depAmount | term | mTopUp | percent   | replenishment | withdraw   |
      | Рубли       | 300 000   | 6    | 50 000 | 12 243,26 | 250 000       | 562 243,26 |
      | Доллары США | 500 000   | 12   | 20 000 | 920,60    | 220 000       | 720 920,60 |
