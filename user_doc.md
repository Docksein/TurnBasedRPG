# Turn-Based RPG Game - Uživatelská dokumentace

## Úvod
Vítejte v textové turn-based RPG hře! Jedná se o dungeon crawl, ve kterém prozkoumáváte místnosti,
bojujete s nepřáteli, sbíráte předměty a díky zkušenostem sílíte. Tím že to je relativně jednoduchá hra,
není vůbec vybalancovaná a mapa je velmi malá - ovšem dá se měnit pomocí přiloženého textového dokumentu.

## Začátek

Při spuštění hry začínáte s na úrovni 1 s následujícími údaji:
- 100 životů
- 0 Zkušeností (XP)
- Bez meče (Pěsti - 10 damage)
- Prázdný inventář s velikostí 10ti položek

## Ovládání
Hra používá číslované nabídky 1-n podle počtu možností. Zadejte číslo odpovídající požadované akci a stiskněte klávesu
Enter.

## Navigace v místnostech

### Přesun mezi místnostmi
- V hlavní nabídce vyberte možnost "Přesun do jiné místnosti".
- Vyberte jeden z dostupných směrů (sever, jih, východ, západ).
- U každé místnosti se zobrazí její název, popis a dostupné východy.
- Nebo můžete zvolit možnost "Zůstat zde" a zůstat v aktuální místnosti.

### Stavy místností
- **Nenavštívené místnosti**: Obsahují nepřátele a lze je prozkoumat pro nalezení předmětům
- **Navštívené místnosti**: Neobjeví se žádní noví nepřátelé, ale lze je prozkoumat, pokud jste tak neučinili dříve.

## Bitva proti nepřátelovi

### Typy nepřátel
- **Běžní nepřátelé**
- **Bossové**

### Nepřátelské spawningy
- Nepřátelé se objevují při vstupu do nenavštívených místností
- Nepřátelé-bossové se spawnují v místnostech, které mají v názvu slovo "Boss".
- Úrovně nepřátel jsou počítané podle vaší úrovně
- Bossové mají dvojnásobné životy a dávají více XP

### Chování nepřátel
- Nepřátelé mají dva typy útoků:
    - **Lehký útok**: Standardní poškození podle úrovně nepřítele
    - **Těžký útok**: 150% poškození (30% šance na použití)
- Nepřátelé pouze útočí - nehlídají a nepoužívají předměty.

## Bojový systém

### Akce v boji
1. **Útok**: Uděluje poškození vybavenou zbraní
2. **Obrana**: Obranný postoj se dvěma možnými výsledky:
    - 50% šance na úplné zablokování (bez poškození)
    - 50% šance na snížení poškození o polovinu
3. **Použití předmětu**: Přístup do inventáře a použití zvoleného předmětu
4. **Útěk**: Pokus o útěk (70% úspěšnost, není k dispozici proti bossům).

### Průběh boje
- Boj je tahový: nejprve jednáte vy, pak nepřítel
- Vaše životy a životy nepřítele se zobrazují v každém tahu
- Boj pokračuje, dokud není jedna strana poražena nebo dokud úspěšně neutečete

## Předměty a inventář

### Typy předmětů

#### Zbraně
- Můžete nahradit svou aktuálně vybavenou zbraň
- Poskytují bonus k poškození při útocích
- V jednu chvíli lze vybavit pouze jednu zbraň

#### Zdravotní lektvary
- Při použití okamžitě obnoví životy
- Množství uzdravených životů se mění s úrovní lektvaru
- Spotřebuje se okamžitě po použití

#### Lektvary síly
- Poskytují dočasné zvýšení poškození
- Účinek trvá pouze po dobu aktuálního bojového střetnutí
- Zvýšení poškození se mění s úrovní lektvaru

### Správa inventáře
- **Kapacita**: 10 slotů na předměty a 1 slot na zbraň
- **Mimo boj**: Pomocí možnosti "Správa inventáře" můžete:
    - Použít předměty / Vyměnit zbraň
    - Vyhodit nepotřebné předměty
- **Během boje**: Použijte akci "Použít předmět" ke spotřebování lektvarů.

### Hledání předmětů
- **Předměty od nepřátel**: 50% šance při porážce jakéhokoli nepřítele
- **Prozkoumávání místností**: 60% šance při průzkumu místnosti (jednou za místnost)
- Předměty stupňují podle vaší aktuální úrovně

## Vylepšování postavy

### Zkušenosti a levelování
- Získávejte zkušenosti porážením nepřátel:
    - Běžní nepřátelé: 10 XP × úroveň nepřítele
    - Bossové: 50 XP × úroveň nepřítele
- Každých 100 získaných XP zvyšuje úroveň
- Každá úroveň zvyšuje:
    - Maximální zdraví o 20 bodů
    - Aktuální zdraví se obnoví na maximum

### Statistiky postavy
- **Životy**: Počet životů, hra končí, pokud dosáhnete 0
- **Úroveň**: Určuje obtížnost nepřátel a kvalitu předmětů
- **XP**: Zkušenostní body pro další úroveň
- **Vybavená zbraň**: Aktuální zbraň a její poškození


## Konec hry

### Vítězství
- Pokud porazíte bosse:
    - Pokračujte ve hře a prozkoumejte další oblasti
    - Ukončete hru

### Konec hry
- Nastane, když vaše životy dosáhnou 0
- Žádný systém ukládání - hra se restartuje
