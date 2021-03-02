# CyberGooseDiscounts

Лабораторна робота номер 2 

## Сортування «Американський прапор» :: American flag sort

У нашій програмі ми будемо використовувати функцію сотрування акцій цін для перегляду найдешевших або найдорожчих

ЩО саме із себе представляє амереканський флаг
![example](dopinfo/Uncle_Sam.jpg)

Коли у нас не два, не три, а будь-яку кількість розрядів, ми фіксуємо, де повинен починатися кожен розряд (його «смуга») і перераскідиваем елементи за своїми «смугах».

У цьому алгоритмі числа зазвичай розглядаються не як десяткові, а в інший розрядності, найчастіше є ступенем двійки. Найчастіше в якості підстави для розрядності береться число 256 (трохи рідше 128), що дозволяє ефективно адаптувати сортування для упорядкування рядків. Для чисел розрядність зручніше брати невеликі 2n (2, 4, 16 і т.д), що дозволяє порівнювати шляхом зсуву по бітам, замість піднесення до степеня при порівнянні десяткових чисел.


В анімації показано на прикладі розрядності з основою 16:
![example](dopinfo/tmmf.gif)

Простіші версії цієї сортування - двоколірний прапор і триколірний голландський прапор. Візуалізація дуже схожа на сортування аппроксимацией.

1. При першому проході - пошук максимуму з метою визначити максимальну кількість розрядів серед елементів в масиві (для того щоб коректно витягати певні за рахунком розряди з елементів).
2. Потім відбувається рекурсивна обробка. При виклик зазначаються межі подмассіва і поточний оброблюваний розряд. На першому виклику подмассіви є весь масив, береться найперший розряд зліва.
3. Серед елементів подмассіва здійснюється початковий підрахунок - скільки разів кожна цифра зустрічається в поточному розряді. Цей посдчёт дозволяє визначити локалізацію для цих цифр розрядів (тобто відомі межі і місцезнаходження «смуги», в яку потрібно перемістити ті елементи, які мають чергову цифру в певному розряді). Власне, локалізації - це покажчики на «смуги», куди потрібно переміщувати відповідні елементи.
4. Відповідно до локалізаціями-покажчиками відбувається обмін на місці - по цифрі в розряді видно, куди потрібно відправити елемент, на його місце приходить інший елемент, з яким стався обмін. Цей пункт виконується до тих пір, поки при черговому обміні прийшов з іншого місця елемент не виявиться на своєму місці (тоді можна переходити до наступного елементу подмассіва і аналогічно для нього виконати цей пункт).
5. Після того, як в результаті обмінів перерозподілили елементи в блоки по цифрам в черговому розряді, відбувається рекурсія - до кожного блоку як до подмассіви застосовується цей же алгоритм, як поточного розряду береться наступний за рахунком.