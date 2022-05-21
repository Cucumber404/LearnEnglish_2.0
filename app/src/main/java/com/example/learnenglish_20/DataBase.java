package com.example.learnenglish_20;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.learnenglish_20.modal.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataBase {

    public DatabaseReference mWordDataBase;
    public static DatabaseReference mUserDataBase;
    private final String WORD_KEY = "Word";
    public static final String USER_KEY = "User";
    public static List<Word> wordsArr;
    public static User myCurrentUser;
    StorageReference storageRef;
    public static String[] englishWordsArr; // При необходимости изменения данных в базе заполнить данные массивы
    public static String[] russianWordsArr; // и вызвать pushInDB
    public static int progress;
    public static boolean isFirstTime;
    public static boolean flagGotUser=false;

    static {
        new DataBase();
    }

    DataBase() {
        mWordDataBase = FirebaseDatabase.getInstance().getReference(WORD_KEY);
        mUserDataBase = FirebaseDatabase.getInstance().getReference(USER_KEY);
        FirebaseStorage storage = FirebaseStorage.getInstance();
        storageRef = storage.getReference();
        wordsArr = new ArrayList<>();
//        pushInDB(); // В наст момент база заполнена
        getDataFromDB();
        getMyUserFromDB(FirebaseAuth.getInstance().getCurrentUser().getEmail());
    }

    public static void getMyUserFromDB(String eMail) {
        ValueEventListener vListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    myCurrentUser = ds.getValue(User.class);
                    assert myCurrentUser != null;
                    if (myCurrentUser.geteMail().equals(eMail)) {
                        break;
                    }
                }
                //Когда код доходит до сюда, вся информация уже загружена, то
                //есть можно отследить момент загрузки (важно!!!)
                progress = Integer.parseInt(String.valueOf(myCurrentUser.getProgress()));
                isFirstTime=myCurrentUser.isFirstTime();
                flagGotUser=true;
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        mUserDataBase.addValueEventListener(vListener);
    }

    public static void updateProgress() {
        ValueEventListener vListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    myCurrentUser = ds.getValue(User.class);
                    assert myCurrentUser != null;
                    if (myCurrentUser.geteMail().equals(FirebaseAuth.getInstance().getCurrentUser().getEmail())) {
                        ds.child("progress").getRef().setValue(String.valueOf(progress));
                        break;
                    }
                }
                //Когда код доходит до сюда, вся информация уже загружена, то
                //есть можно отследить момент загрузки (важно!!!)
                progress = Integer.parseInt(String.valueOf(myCurrentUser.getProgress()));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        mUserDataBase.addValueEventListener(vListener);
    }

    public static void updateFirstTime() {
        ValueEventListener vListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    User tempUser = ds.getValue(User.class);
                    assert tempUser != null;
                    if (tempUser.geteMail().equals(myCurrentUser.geteMail())) {
                        ds.child("firstTime").getRef().setValue(false);
                        break;
                    }
                }
                //Когда код доходит до сюда, вся информация уже загружена, то
                //есть можно отследить момент загрузки (важно!!!)
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {}
        };
        mUserDataBase.addValueEventListener(vListener);
    }

    private void getDataFromDB() {
        ValueEventListener vListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (wordsArr.size() > 0) wordsArr.clear();
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    Word word = ds.getValue(Word.class); // Получаем Word из БД
                    assert word != null; // Проверяем что word не null
                    wordsArr.add(word);
                    System.out.println(word.getEnglish());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        mWordDataBase.addValueEventListener(vListener);
    }

    private void pushWordsInDB() { // При необходимости
        String id = mWordDataBase.getKey();
        for (int i = 0; i < englishWordsArr.length; i++) {
            Word word = new Word(id, englishWordsArr[i], russianWordsArr[i], String.valueOf(i));
            mWordDataBase.push().setValue(word);
        }
    }

    public static void pushUserInDB(String eMail) { // При необходимости
        mUserDataBase = FirebaseDatabase.getInstance().getReference(USER_KEY);
        String id = mUserDataBase.getKey();
        User user = new User(id, "0", eMail,true);
        mUserDataBase.push().setValue(user);
    }

//    Данные массивы использовались при заполнении БД, понадобятся только
//    при изменении
//    englishWordsArr = new String[] {"absorb", "accord", "across", "adolescent", "advise", "aftermath", "air", "all along", "alter", "ammunition", "anniversary", "apart from", "appraise", "archive", "article", "asleep", "assure", "attain", "available", "axe", "bail", "banner", "bartender", "be back", "bear", "behave", "beneath", "bicycle", "bizarre", "blend", "blow", "bog", "border", "bow", "brave", "breath", "bring along", "broth", "built-in", "burden", "butter", "cable", "candy", "carcass", "carve", "cater", "cell", "chancellor", "chat", "cheerful", "chip", "cinema", "clamp", "clench", "close in on", "coal", "cold", "come along", "come to life", "commit", "compassion", "compress", "conduct", "connect", "console", "contempt", "converge", "cork", "count", "courtesy", "crank", "creek", "cross", "crush", "curl", "cut down", "dash", "deal with", "decline", "definite", "delusion", "dependent", "deserve", "detect", "diamond", "diligent", "discard", "disease", "dispel", "distinguish", "dizzy", "doom", "drag on", "drip", "dry up", "duo", "earn", "educate", "eliminate", "emphasis", "endeavor", "enrol", "environment", "essence", "evident", "exchange", "exist", "explore", "eyebrow", "fade", "fall for", "faraway", "favor", "fellow", "fiction", "find out", "fit in with", "flavor", "flinch", "fluent", "fold", "forbid", "forth", "fracture", "freeway", "frost", "fur", "gamble", "geared to", "get along", "get through", "glacier", "gloss", "go off", "gossip", "grasp", "grief", "gross", "guise", "hail", "handful", "hardly", "hawk", "heave", "helpless", "highway", "hitch", "honest", "horrify", "hue", "hurl", "ill", "implicate", "incidence", "indulge", "infuse", "innocent", "insulate", "intercept", "intimate", "involve", "janitor", "joint", "junior", "kettle", "knack", "lack", "lapse", "laundry", "leak", "legal", "lettuce", "light", "linen", "liven up", "locus", "look into", "lousy", "lush", "make fun of", "mandatory", "marital", "match", "mean", "melon", "mere", "mild", "miracle", "mix up", "monastery", "motion", "mug", "mute", "native", "neighbour", "niche", "nose around", "nudge", "obedience", "obvious", "offspring", "opaque", "ordinary", "outgoing", "oven", "overtake", "pad", "paper", "part-time", "pasture", "pay out", "pee", "perfect", "personnel", "pick up", "pineapple", "plain", "pledge", "point", "poll", "possible", "powerful", "predominant", "press", "prime", "procure", "proof", "prove", "pump", "put aside", "puzzle", "quit", "rainbow", "rapid", "reach out", "receipt", "record", "reflect", "regular", "relieve", "render", "repress", "resist", "restrict", "reveal", "rich", "ring", "roast", "root out", "royal", "run", "rut", "salt", "savings", "scent", "scramble", "scrub", "see to", "sense", "set", "settle on", "shake", "sheep", "shiver", "shred", "shy", "significant", "single", "skid", "slate", "slipper", "smear", "sneak", "sober up", "solitary", "source", "speak out", "spell out", "spokesman", "spy", "stable", "stamp", "stash", "steer", "stifle", "stoop", "strawberry", "strip", "stupid", "substance", "suck", "summary", "supreme", "suspect", "sweetheart", "tack", "take care", "tally", "taunt", "temporary"};
//    russianWordsArr = new String[] {"впитывать, поглощать", "согласие", "за, через", "подросток, подростковый", "советовать", "последствие, результат", "вид, впечатление", "всё время", "изменять", "боеприпасы", "годовщина", "помимо", "оценивать", "архив", "статья", "спящий", "уверять", "достигать", "доступный, в наличии", "топор", "залог", "знамя, транспарант", "бармен", "возвращаться", "нести, втч перен.", "вести себя", "ниже, под", "велосипед", "странный, причудливый", "смешивать", "дуть", "болото, трясина", "граница, край", "бант, бабочка", "храбрый", "дыхание, вздох", "взять с собой", "бульон, отвар", "встроенный", "бремя, нагружать", "масло сливочное", "канат, кабель, трос и т.д.", "конфеты", "туша", "вырезать (напр. из дерева)", "обслуживать, угождать", "ячейка, клетка", "канцлер", "болтать", "весёлый, неунывающий", "щепка, стружка", "кино, кинотеатр", "зажимать, зажим", "стиснуть, сжать", "приближаться", "уголь", "простуда", "продвигаться (о работе)", "оживать, приходить в себя", "совершить (напр. ошибку)", "сочуствие, сострадание", "сжимать, прессовать", "вести, поведение", "соединять", "утешать", "презрение, презирать", "сходиться, сближаться", "пробка", "считаться, иметь значение", "учтивость, этикет", "чудак, фрик", "ручей", "пересекать, пересечение, крест", "раздавливать, дробить", "скручивать, клубиться, завиток", "урезать, уменьшить", "бросать, бросаться", "иметь дело с", "снижаться, снижение", "определённый", "наваждение, заблуждение", "зависимый", "заслуживать", "обнаружить, выявить", "бриллиант обычно алмаз", "усердный, старательный", "отбросить, отказаться", "болезнь", "развеяться, исчезнуть", "выделять, отличать", "головокружительный", "гибель, рок, обречённость и т. д.", "тянуться долго", "капля, капать", "пересохнуть, иссякнуть", "дуэт", "зарабатывать", "воспитывать, обучать", "устранять, ликвидировать", "акцент, внимание, ударение", "стремиться, стараться, попытка", "записать (в члены), набрать", "среда, окружение", "сущность", "очевидный", "обмен", "существовать", "изучать", "бровь", "увядать, блекнуть", "вестись", "далёкий", "услуга", "парень, человек", "вымысел, худ.литература", "выяснить", "вписаться (в коллектив)", "вкус, приправлять", "дёрнуться, дрогнуть", "беглый", "сворачивать, складывать", "запрещать", "дальше, вперёд", "перелом, разрушение", "автострада", "мороз", "мех", "азартная игра, играть", "направлен на", "ладить", "дозвониться", "ледник", "глянец, лоск", "отключиться", "слухи", "схватывать", "горе", "грубый (во всех смыслах)", "облик, личина", "окликнуть, привлечь внимание", "горсть", "едва", "ястреб", "вздыматься", "беспомощный", "шоссе", "заминка, помеха", "честный", "ужасать", "цвет, оттенок", "метать, бросать с силой", "дурной, нехороший", "указать на причастность", "частотность, распространённость", "баловать, потворствовать", "вливать, настаивать", "невинный, невиновный", "изолировать, оградить", "перехватить, перехватить", "близкий", "содержать в себе (процесс)", "дворник, уборщик", "сустав, шарнир, стык и т. д.", "младший", "чайник", "умение, сноровка", "недостаток, нуждаться", "упущение, оплошность", "прачечная", "утечка, просачиваться, протекать", "законный, правовой", "салат (латук)", "зажигать", "льняная ткань", "оживить, взбодрить", "местоположение", "посмотреть, изучить", "паршивый", "пышный", "смеяться, издеваться", "обязательный", "супружеский", "соответствие, нечто соответствующее", "иметь в виду", "дыня", "всего лишь", "мягкий", "чудо", "перепутать", "монастырь", "движение", "кружка", "беззвучный, не говорящий", "родной", "сосед", "ниша", "рыскать, вынюхивать", "подталкивать", "покорность", "очевидный", "отпрыск или всё потомство", "непрозрачный", "обычный", "уходящий", "печь, духовка", "обгонять", "подушечка пальца, лапы", "бумага, документ", "неполная занятость", "пастбище", "выплатить (крупную сумму)", "писать", "совершенный", "персонал, личный состав", "подобрать", "ананас", "ровный, равнина", "обещание, обязательство", "смысл, суть", "опрос или голосование", "возможный", "мощный, действенный", "преобладающий", "нажимать", "важный, главный, первый", "достать, добыть, обеспечить", "доказательство (неисч.)", "доказывать", "насос, нагнетать", "отложить", "озадачить", "бросить, покинуть, перестать", "радуга", "быстрый", "потянуться (рукой)", "получение", "запись", "отражать(ся)", "обычный", "облегчить, освободить", "делать, придавать свойство", "подавлять", "сопротивляться", "ограничивать", "раскрыть, выявить", "богатый", "звонить, звонок", "жарить", "искоренить", "королевский", "управлять", "борозда, колея, рутина", "соль", "сбережения", "нюх или аромат", "драка, схватка", "чистить, драить", "заняться, позаботиться", "чувство, осознание", "набор, комплект", "определиться с", "пожать (руки)", "овца", "дрожь, дрожать", "мелко порубить", "застенчивый", "значительный", "один, отдельный, единый", "заносить, скользить, буксовать", "шифер или сланец", "тапок", "мазать", "красться", "протрезветь", "уединенный", "источник", "высказать", "разъяснить", "представитель, прес-секретарь", "шпион, шпионить", "устойчивый", "печать, штамп, марка", "тайник, заначка, прятать", "управлять, направлять", "удушать", "наклоняться или сутулиться", "клубника", "полоса, лента", "глупый", "вещество, материя", "сосать", "сводка, конспект", "высший, верховный", "подозревать, подозреваемый", "возлюбленный (ая), милый", "курс, линия поведения", "позаботиться, заняться", "подсчитывать, подсчёт, итог", "насмехаться, насмешка", "временный"};


}
