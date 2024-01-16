--liquibase formatted sql
--changeset sergey:2
INSERT INTO categories(name)
VALUES ('Пуэр'),
       ('Улун'),
       ('Белый чай'),
       ('Красный чай'),
       ('Зеленый чай');

--changeset sergey:3
INSERT INTO products(name, price, category_id, description, effect, stock_quantity)
VALUES ('Шень Шань Лао Шу', 10, 1, 'Лао Тун Чжи - это качество Мэнхайской чайной фабрики без подделок. Сама компания основана Чжоу Бин Ляном - бывшим управляющим и технологом Мэнхайской чайной фабрики, и Лю Гуалин - уважаемого эксперта пуэра в Юньнани.
Чжоу Бин Лян - это тот самый человек, придумавший рецепт изготовления Шу Пуэра в 1973 году и при помощи Ву Ци Ин, президента Куньминской чайной фабрики, они запустили его массовое производство в 1975г. Так что эти двое точно знают немного о чае.
Хоть фабрика и молода по сравнению с конкурентами и существует всего с 1999 года - это один из самых популярных пуэров в Китае и регионе. С 2003 года компания наладила экспорт своего чая и сегодня половина продукции продается за рамками Китая, в основном в Японии и Корее. Увы, в Европу чая доезжает значительно меньше.
Но мы исправим это недоразумение.
Потому что каждый чай этой фабрики - прекрасен.
Лао Тун Чжи продается еще под брендом Хайвань - это та же фабрика.
Хоть их девизом и является "Хороший чай для каждого", а подсолнухи на их логотипе намекают на простецкий дух их продукта - их чай один из самых дорогих в регионе.
Но заслужено.
Чайная фабрика каждый год растет все больше, производство увеличивается, а чай все равно разлетается, как горячие пирожки.',
        'Оказывает тонизирующий эффект, обладает очищающими свойствами, хороший антиоксидант', 1000);

INSERT INTO productimages(product_id, image_url)
VALUES (1,
        'https://dl.dropboxusercontent.com/scl/fi/05uc58n9m5il5f15xls9y/1-removebg-preview.png?rlkey=gcsy31i8syw5yin5o8qk9g0o7&raw=1'),
       (1,
        'https://dl.dropboxusercontent.com/scl/fi/hrtvhjn6lh4mz08cmpskd/2-removebg-preview.png?rlkey=pkt3cd6cmvfparlbddcwpk9ic&raw=1'),
       (1,
        'https://dl.dropboxusercontent.com/scl/fi/joivf9opmy0t2hovb37ha/3-removebg-preview.png?rlkey=36z3grkdsx7z92q643tixikwk&raw=1');

INSERT INTO products(name, price, category_id, description, effect, stock_quantity)
VALUES ('И У ШАНЬ', 21, 1, 'Мы постоянно ищем, чем пополнить ассортимент. На данный момент у нас есть большинство популярных чаев и есть постоянные поставщики. Пришло время закупать уникальные чаи, которые сложно найти в продаже. Мы вышли на маленькую фабрику Дэнхуан, которая занимается только пуэрами высокого качества, в основном моносырье. На больших фабриках почти все блины готовятся из купажированного сырья, что помогает выровнять вкус и из года в год поставлять одинаковый продукт. Механическая работа и полная автоматизация далеко не всегда приводит к хорошему результату. Когда вы берете блин от Дэнхуан, его можно разломать руками, настолько легкая прессовка. Листья остаются целыми при разделывании. Все благодаря тому, что ребята закупают сырье у производителей и сами занимаются выдерживанием листа, ферментацией, прессовкой и упаковкой. 90% их продаж - на местный рынок и только 4% уходит на Европу и 1% на Северную Америку.
В горах И У (易武山) специализируются на изготовлении шен пуэров. Из этого региона не выходят на никакие другие чаи, за парой исключений. Именно тут начинался знаменитый конный маршрут идущий на север, в город Пуэр (普洱). Хоть самые старые деревья находятся у соседей в горах Бада, здесь тоже есть чем похвастаться: возле Ло Шуй растет дерево возрастом 700 лет, а местная народность Булан одной из первых стали готовить пуэр. Именно от сюда впервые начали поставлять этот чай в императорский доврец, что и сделало его знаменитым.
Если вы любите шен пуэры, то попробовав пуэры Дэнхуан, вы не захотите больше никакой другой.',
        'Оказывает расслабляющий и успокаивающий эффект', 1000);

INSERT INTO productimages(product_id, image_url)
VALUES (2,
        'https://dl.dropboxusercontent.com/scl/fi/rgqrixgrbobcwp27rx9si/1-removebg-preview.png?rlkey=6dotfcwvuuzuj17xkbk1s1fpg&raw1'),
       (2,
        'https://dl.dropboxusercontent.com/scl/fi/fdm5p5sa5bvt4aeuyqz0i/2-removebg-preview.png?rlkey=797nweno58d03rqoiz4twn56r&raw=1'),
       (2,
        'https://dl.dropboxusercontent.com/scl/fi/e1s7lauezq1lzcksd8p1m/3-removebg-preview.png?rlkey=cf78tti01o4a46mitnbnpguo3&raw=1');

INSERT INTO products(name, price, category_id, description, effect, stock_quantity)
VALUES ('Бань Чжань Дзинь Я', 28, 1,
        'Поживший шу пуэр из Бан Чжан имеет коричнево-рыжий оттенкок и древесно-скирдяной аромат. Мутный настой тёмного-коричневого цвета обладает горько-сладким вкусом с нотками чернослива, коры хвойного дерева, мускатного ореха. Получаем яркий тонус, много энергии и без излишеств.',
        'Омолаживает кожу. Выводит вредные вещества, шлаки и токсины из организма. Обладает "антипохмельным" свойством и борется с негативным влиянием "тяжелой" пищи.',
        1000);

INSERT INTO productimages(product_id, image_url)
VALUES (3,
        'https://dl.dropboxusercontent.com/scl/fi/ttzmnwsgxzrzpe0rautn0/1-removebg-preview.png?rlkey=sy395n636qglo8odsyj4g1xcv&raw=1');

INSERT INTO products(name, price, category_id, description, effect, stock_quantity)
VALUES ('Бо Хе Тан Пуэр', 25, 1,
        'Блинчик тёмно-зелёного и коричневого цвета с обилием почек. Двухсотграммовая лепёшка средней плотности пресса. На прогреве дыханием сухой лист имеет аромат нектара цветов и спелых фруктов. При добавлении воды в 86° получаем прозрачный золотистый настой. Нежный вкус с мягкой сладостью красного яблока, мёда, груши и малость гороха. Бергамот, мандарин, зелёное яблоко и орхидея представляют кислую сторону вкуса. Во рту слегка вяжет и подсохло, но горечи нет, если не перезаварить конечно. Послевкусие цветочно-фруктовое с балансом кислоты и сладости. Состояние лёгкое и бодрое. Чай пробуждает, проясняет зрение, очищает разум и добавляет внутренних ресурсов.',
        'Оказывает бодрящее действие на организм, хорошо повышает тонус, улучшает пищеварение и ускоряет обмен веществ',
        1000);

INSERT INTO productimages(product_id, image_url)
VALUES (4,
        'https://dl.dropboxusercontent.com/scl/fi/1r37zgkpemu455tnu8mxn/1-removebg-preview.png?rlkey=00ti1y9cdmll2cllr65liimnk&raw=1'),
       (4,
        'https://dl.dropboxusercontent.com/scl/fi/ay35paa55c2s5p71euo58/2-removebg-preview.png?rlkey=z8k8v7boni6qhnr1zevdgygev&raw=1'),
       (4,
        'https://dl.dropboxusercontent.com/scl/fi/3iehpgse0wtuvyoq764ue/3-removebg-preview.png?rlkey=37ufdbvxjmfins44hi6tc5cyi&raw=1');

INSERT INTO products(name, price, category_id, description, effect, stock_quantity)
VALUES ('Бу Лан Шань Пу Эр', 30, 1,
        'Юный шу пуэр с горы Булан имеет красивый внешний вид сухого листа и выраженный дуйвэй (скирдяной аромат), который часто путают с различными оттенками рыбы. Велика вероятность, что через годик-другой он уляжется или вовсе исчезнет. Непрозрачный, тёмно-коричневый настой обладает бархатистым вкусом со сладостью грецкого и мускатного ореха, немного какао. После глотка наблюдается лёгкая сухость в полости рта и горчинка яблочной косточки. Приятный мягкий тонус, чистота зрения и ясность рассудка, повышенная работоспособность. Хотелось бы попробовать этот пуэр через пару лет, ведь годы наверняка пойдут ему на пользу.',
        'Оказывает бодрящее действие на организм, хорошо повышает тонус, улучшает пищеварение и ускоряет обмен веществ',
        1000);

INSERT INTO productimages(product_id, image_url)
VALUES (5,
        'https://dl.dropboxusercontent.com/scl/fi/6vpyt20x7v9u2flq6hmft/1-removebg-preview.png?rlkey=bhyofrgxt0rm40g0r8nlfakcn&raw=1'),
       (5,
        'https://dl.dropboxusercontent.com/scl/fi/4pnaui98dwoafqyv2ix7e/2-removebg-preview.png?rlkey=4cd26atsl4uu2ov3hdbjzqjo3&raw=1'),
       (5,
        'https://dl.dropboxusercontent.com/scl/fi/iw15kk17qfttj1oyrsax1/3-removebg-preview.png?rlkey=vxs3hmcal65ze2g2arw1ek2ew&raw=1');

INSERT INTO products(name, price, category_id, description, effect, stock_quantity)
VALUES ('Ци Цзы Бин Ча 7572', 70, 1, 'Ци Цзы Бин Ча 7572
Лепешка семерых братьев, классический рецепт 7572 (Да И, шу пуэр)
Чай обладает очень богатым ореховым вкусом со множеством оттенков. Заваривается продолжительно и насыщенно. Спрессован, в основном, из старых листьев, покрытых золотым пушком. На первых заварках он — мягкий и прозрачный, цвета коньяка; по вкусу — мягкий и ароматный. Но потом интенсивность цвета настоя и вкус начинают нарастать, становятся гуще. Очень вкусный пуэр, всем рекомендуем познакомиться с ним. Не забывайте, что именно на Мэнхайской фабрике был изобретен метод приготовления шу пуэров, а этот чай — эталонный шу пуэр. У нас иногда продается его аналог 2004 года и с выдержкой на сыром складе. Очень интересные образцы для сравнения.
Шу пуэр 7572, а так же шен пуэр 7542 являются классикой Мэнхайской фабрики и самыми популярными в мире шен и шу пуэрами. Они признаны образцами вкуса для других пуэров. Название рецептов 7542 и 7572 говорит о том, что рецепты были разработаны в 1975 году. Последняя цифра 2 указывает на производителя — Мэнхайская фабрика (сейчас выходят под брендом Да И). Предпоследняя цифра показывает, что для шен пуэра использовали молодые листочки с почками (4), а для шу пуэра — старые листья (7).
Как ни странно, но под этими рецептами выпускаются чаи и на других фабриках. Но все же самые лучшие, вкусные и общепризнанные — только с Мэнхайской фабрики, бренд Да И.',
        'Прекрасно бодрит и согревает. Улучшает настроение и гармонизирует внутреннее состояние.', 1000);

INSERT INTO productimages(product_id, image_url)
VALUES (6,
        'https://dl.dropboxusercontent.com/scl/fi/co7y1vr6so6zqtg4e4x04/1-removebg-preview.png?rlkey=ay6u34frkeyiuhst3emwrk88e&raw=1'),
       (6,
        'https://dl.dropboxusercontent.com/scl/fi/wc4rmdm4xfh2q5kz7eqr7/2-removebg-preview.png?rlkey=alp4irt1ahpervpn89bmj0na1&raw=1'),
       (6,
        'https://dl.dropboxusercontent.com/scl/fi/2qic233c2mtmrmaxhzi58/3-removebg-preview.png?rlkey=ggylftj3cwoh3ztttoo3o8qrd&raw=1');

INSERT INTO products(name, price, category_id, description, effect, stock_quantity)
VALUES ('Ту Линь Фэн Хуан Точа 803', 30, 1,
        'ЮОдин из лучших рецептов шу пуэра от фабрики Ту Линь. То Ча весом в 336 грамм приготовлена из премиального, сертифицированного сырья с чайного сада Nan Jian Tulin, что находится в горах У Лян Шань. Да и сама приставка «Тэ Цзы» дословно переводится как «Высший сорт». В купаже используются только среднего размера листья и большое количество чайных почек, что придает чаю особый шарм и элегантность.',
        'Бодрит и зажигает.', 1000);

INSERT INTO productimages(product_id, image_url)
VALUES (7,
        'https://dl.dropboxusercontent.com/scl/fi/lprwslwp3heljc7502v0y/1-removebg-preview.png?rlkey=dkk7paeywdqn86ekavs2nmn0b&raw=1'),
       (7,
        'https://dl.dropboxusercontent.com/scl/fi/6lb3aaofm9dmkda4i5wg3/2-removebg-preview.png?rlkey=6ky5ix0ovqg956f4usop6fvzv&raw=1'),
       (7,
        'https://dl.dropboxusercontent.com/scl/fi/wxcscb2bt8zeeenvgljdm/3-removebg-preview.png?rlkey=nqzna47vs1p21naj14unh8apf&raw=1');

INSERT INTO products(name, price, category_id, description, effect, stock_quantity)
VALUES ('Цзы цзюань пуэр', 42, 1,
        'Молодой шэн пуэр из фиолетового сырья. Крупный тёмный, практически чёрный лист, не плотно спрессован в блин. На прогреве дыханием кисло-сладкий аромат цветов, ягод и фруктов. При помощи воды в 88° получаем золотисто-зеленый слегка туманный тягучий настой. Во вкусе обволакивающая сладость абрикоса, дыни, груши, гречишного мёда и немного ванили. Играющая кислинка розы, мандарина и жёлтой сливы. При перезаваривании горечи не даёт, но терпкость будет приличная. Послевкусие слегка подсушивающее полость рта со сладостью фруктов и лёгкой цитрусовой кислинкой. Состояние лёгкости, мягкого тонуса, ясности и остроты мысли, наполненности и позитивных эмоций.',
        'Скорее тонизирует, Собирает с мыслями.', 1000);

INSERT INTO productimages(product_id, image_url)
VALUES (8,
        'https://dl.dropboxusercontent.com/scl/fi/0aonk84njrujve7mdirmw/1-removebg-preview.png?rlkey=fcipmo7en8qwlpec774dw8d7l&raw=1'),
       (8,
        'https://dl.dropboxusercontent.com/scl/fi/vhapiy986bxh4u97a5ezw/2-removebg-preview.png?rlkey=k97sce6f9oisckreb16f1v455&raw=1'),
       (8,
        'https://dl.dropboxusercontent.com/scl/fi/58yz7pc10y6ahfcm5gezu/3-removebg-preview.png?rlkey=h9t4vq28t6jhyi8q00fnm0qrl&raw=1');

INSERT INTO products(name, price, category_id, description, effect, stock_quantity)
VALUES ('Бай Хао Инь Чжэнь', 39, 3,
        'Бай Хао Инь Чжэнь (Байха́о Иньчжэ́нь) или «Серебряные иглы с белым ворсом» – знаменитый белый чай, изготовленный из весенних, готовых к раскрытию, почек кустов Да Бай Ча в провинции Юньнань. В Китае о Байхао Иньчжэнь говорят: «Белый как облако, зеленый как мечта, чистый как снег, ароматный как орхидея». Элегантный, утонченный и свежий, с мягкими нотами весенних цветов, меда, спелых лесных ягод и нюансами яблок. Отлично подходит для варки на открытом огне. Приятно собирает, приободряет, наполняет силами и энергией, при этом создает атмосферу легкости и спокойствия.',
        'Снижает артериальное давление, укрепляет иммунитет, а благодаря высокому содержанию витаминов, аминокислот и пектинов омолаживает весь организм, улучшает холестериновый обмен, а так же обладает жаропонижающими свойствами.',
        1000);

INSERT INTO productimages(product_id, image_url)
VALUES (9,
        'https://dl.dropboxusercontent.com/scl/fi/wvkomlqnto987n6k5t6ud/1-removebg-preview.png?rlkey=pr3pfsfvdce0spt89oulo4y2c&raw=1'),
       (9,
        'https://dl.dropboxusercontent.com/scl/fi/izycrye0kbesbhykb1ws3/2-removebg-preview.png?rlkey=sqsz5x545elvfncid1yfwu6du&raw=1'),
       (9,
        'https://dl.dropboxusercontent.com/scl/fi/on3jctsm8h0kssyifr6tq/3-removebg-preview.png?rlkey=6v05anpxj016u1ckmg34ztclp&raw=1');

INSERT INTO products(name, price, category_id, description, effect, stock_quantity)
VALUES ('Бай Му Дань Бинг Ча', 82, 3,
        'Бай Му Дань («Белый Пион») – один из самых популярных северофуцзянских белых чаев Китая. Собран и приготовлен ранней весной в Чжэнь Хэ, родине белого чая. К нему применим термин «Первая Весна» (Тоу Чунь, 头春). Это самый первый сбор фудинского чая, самый ценный. Только самые верхние побеги (почка с двумя листиками). Вся мощь, всё, что накопилось за зиму – находится в нем. Характерный размер и количество почек, запоминающийся легкий вкус и аромат – все это создает особенный чай, который дарит наслаждение.',
        'Дает освежающий и дезинфицирующий эффект, по мнению китайских ученых, отлично убирает сильный жар, улучшает эмоциональное состояние.',
        1000);

INSERT INTO productimages(product_id, image_url)
VALUES (10,
        'https://dl.dropboxusercontent.com/scl/fi/9voisah59dp2siz4d0o0o/1-removebg-preview.png?rlkey=0lobl4zh6dix2d9xkmdklj85v&raw=1'),
       (10,
        'https://dl.dropboxusercontent.com/scl/fi/bhc0v47n1xgz9gqv13imq/2-removebg-preview.png?rlkey=x6dozx36j1rzmf4qdkwxua31q&raw=1'),
       (10,
        'https://dl.dropboxusercontent.com/scl/fi/984bux5p4r5drqnxr7his/3-removebg-preview.png?rlkey=dz9mf8j2rbvikbonk0gyfskxo&raw=1');

INSERT INTO products(name, price, category_id, description, effect, stock_quantity)
VALUES ('Би Ло Чун', 82, 5,
        'Свежий билочунь с большим количеством белых пушистых почек, покрытых ворсом. «Изумрудные Спирали Весны» – традиционный зеленый чай из провинции Цзянсу. Урожай собранный весной 2023. Персики, абрикосы, сливы растут бок о бок с чайными кустами, что не может не сказаться на нюансах вкуса и аромата самого чая. Утонченный, свежий, легкий чай с основной цветочно-травянистой гаммой и нюансами летних ягод, фруктовой сладости и тыквенных семечек. Свежесть, ясность и чистота – первые приходящие ассоциации. Отлично освежает, собирает ум и мягко тонизирует.',
        'Нормализует давление, укрепляет стенки сосудов, способствует снижению веса и нормальному обмену веществ, повышает умственную и физическую работоспособность, укрепляет иммунитет, снижает уровень холестерина в крови, укрепляет зубы и десны.',
        1000);

INSERT INTO productimages(product_id, image_url)
VALUES (11,
        'https://dl.dropboxusercontent.com/scl/fi/hsxc2ohydlxy5i7fnzigz/1-removebg.png?rlkey=zijlydaokiexrrsfzsil0xhnc&dl=&raw=1'),
       (11,
        'https://dl.dropboxusercontent.com/scl/fi/bmfmls2cqsupki8pkwone/2-removebg.png?rlkey=c7tf1ze5pwbvj6eqwj0cyocuf&raw=1');

INSERT INTO products(name, price, category_id, description, effect, stock_quantity)
VALUES ('Генмайча', 5, 5,
        'Генмайча – уникальный по своему составу японский чай. Он состоит из двух главных компонентов, это зеленый чай сэнтя, и обжаренный коричневый рис. Такой оригинальный состав придает изысканность не только вкусу но и внешнему виду заваренного чая. Заваривать данный напиток следует в прогретой посуде в течение трех-пяти минут.',
        'Cпособствует укреплению иммунитета, очищению организма, восстановлению сил, улучшению пищеварения и обменных процессов.',
        1000);

INSERT INTO productimages(product_id, image_url)
VALUES (12,
        'https://dl.dropboxusercontent.com/scl/fi/nkztv64bwsa3xbbxhg2g0/1-removebg-preview.png?rlkey=bpmy2yzv26e1fq84uljvwsdu8&raw=1'),
       (12,
        'https://dl.dropboxusercontent.com/scl/fi/9rx9nzhzbz6cmolh5o3hu/2-removebg-preview.png?rlkey=bja7swn3ef6wtpqpkmqpyciyd&raw=1'),
       (12,
        'https://dl.dropboxusercontent.com/scl/fi/lxsc8ynpaogp1ism1mpv3/3-removebg-preview.png?rlkey=h3wpkgp59849y7hm9dndvq4mi&raw=1');

INSERT INTO products(name, price, category_id, description, effect, stock_quantity)
VALUES ('Сун Чжэнь', 25, 5, 'Свежий, яркий и впечатляющий Дянь Хун Сун Чжэнь. Классический вкус “Сосновых Игл”, заряд бодрости и хорошего настроение в каждой чашке. Урожай весеннего сбора 2022 года. Вы будете в него влюблены, однозначно.
Чай приятно и мягко тонизирует, снимает усталость, хорошо согревает и настраивает на рабочий лад.',
        'Cогревающий эффект. Чай плавно тонизирует, наполняет силами, концентрирует внимание, проясняет ум, активизирует мозговую деятельность.',
        1000);

INSERT INTO productimages(product_id, image_url)
VALUES (13,
        'https://dl.dropboxusercontent.com/scl/fi/dmqac2dljp46jn2uy9q9w/1-removebg-preview.png?rlkey=y1p9piaqw5dco0hb38wc3zee1&raw=1'),
       (13,
        'https://dl.dropboxusercontent.com/scl/fi/hdfzwu4nx70i92gisl3ps/2-removebg-preview.png?rlkey=6ev53dx96e89g9ow7pnqjconx&raw=1'),
       (13,
        'https://dl.dropboxusercontent.com/scl/fi/zie1vuv1mgcmb3b64dhvp/3-removebg-preview.png?rlkey=nmgce677bzonvrt8dwuffi61x&raw=1');

INSERT INTO products(name, price, category_id, description, effect, stock_quantity)
VALUES ('Си Ху Лун Цзин', 64, 5,
        'Свежий, почечный Сиху Лунцзин (Западное озеро из Чжэцзяна), весенний сбор 2022 года. Именно этот чай, Сиху Лунцзин, является обладателем титула «Знаменитый чай Китая». Территория сбора Сиху Лунцзин строго ограничена. В качестве сырья используются верхние побеги чайного куста. Ровные, плоские и гладкие листочки, длиной около 2 см. О высоком качестве чайного листа говорит легкий пушок кремового оттенка, который остается в чахай при знакомстве с чаем. В аромате прослеживается цветочная свежесть с фруктовыми нотками, вкус ровный и мягкий, оставляет сладкое послевкусие. За счет освещающего и уравновешивающего воздействия этот чай хочется пить долго, причем большими глотками. Рекомендован людям, пребывающем в постоянном поиске и желающим вкусить что-то новое.',
        'Прекрасно бодрит, освежает и умиротворяет.', 1000);

INSERT INTO productimages(product_id, image_url)
VALUES (14,
        'https://dl.dropboxusercontent.com/scl/fi/d21klqcnxc6zf842ajgzv/1-removebg-preview.png?rlkey=ywqjxky9m6uaipv3tmse1hfys&raw=1'),
       (14,
        'https://dl.dropboxusercontent.com/scl/fi/6hjagb2d83xvczsi4z0yx/2-removebg-preview.png?rlkey=ko4dxp4o7mudi557h03hdlhm1&raw=1'),
       (14,
        'https://dl.dropboxusercontent.com/scl/fi/ce47xrugyr2o7tnkyq7z5/3-removebg-preview.png?rlkey=11cxi3191lqzcnjln3ngr315b&raw=1');

--changeset sergey:4
Insert Into users(full_name, email, password_hash)
VALUES ('Alisa', 'alisa@mail.ru', '$2a$12$OX6l312SSuvcOAILp0Laj.TCjQnfdnX/u8bmzGBJW2/nhvx7UMhn.'),
       ('Sanya', 'sanya@mail.ru', '$2a$12$TZdf8upXkKCo1vsMBq4fLO1u5eR2j8oFGnNJwbLupQ1LLBhBN6PEW');


Insert Into roles(role_name)
VALUES ('ROLE_USER'),
       ('ROLE_ADMIN');

Insert Into Users_roles(user_id, role_id)
VALUES (1,1),
       (2,2);
