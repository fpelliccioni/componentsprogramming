package com.componentsprogramming;
import java.io.*;
import java.util.*;


import static com.componentsprogramming.PalindromeTests.*;


class Garbage {
    static int objectsCollected = 0;
    byte[] data = null;

    public Garbage(int size) {
//        Random rnd = new Random();
//        data = new byte[rnd.nextInt(1000)];
        data = new byte[size];

    }

    @Override
    protected void finalize() throws Throwable {

        if (objectsCollected == 0) {
            System.out.println("objectsCreated:       " + GarbageCreator.objectsCreated);
        }


        ++objectsCollected;
//        System.out.println("Garbage finalize()");
//        System.out.print(".");
        super.finalize();
    }

}

class GarbageCreator implements Runnable {
    static final int maxElements = 80000;
    static final int maxGarbageSize = 1500; //500;

    static int objectsCreated = 0;
    ArrayList<Garbage> garbage = new ArrayList<>(maxElements);


    public GarbageCreator() {
        for (int i = 0; i < maxElements; ++i) {
            garbage.add(null);
        }
    }

    public void run() {
        System.out.println("GarbageCreator started");

//        Random rnd = new Random();

        int n = 0;
        while (true) {
//            garbage.set(n, new Garbage(rnd.nextInt(maxGarbageSize)));
            garbage.set(n, new Garbage(maxGarbageSize));

            ++objectsCreated;

            n = (n + 1) % maxElements;

            if (n == 0) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }


//        while (true) {
//
//            new Garbage();
//            ++objectsCreated;
//
////            try {
////                Thread.sleep(50);
////            } catch (InterruptedException e) {
////                e.printStackTrace();
////            }
//
//        }
    }
}

public class Main {

    public static String cloneString(String input){
        return new String(input.toCharArray());
    }

    public static String shuffle(String input){
        List<Character> characters = new ArrayList<>();
        for(char c:input.toCharArray()){
            characters.add(c);
        }
        StringBuilder output = new StringBuilder(input.length());
        while(characters.size()!=0){
            int randPicker = (int)(Math.random()*characters.size());
            output.append(characters.remove(randPicker));
        }
//        System.out.println(output.toString());
        return output.toString();
    }

    public static ArrayList<String> getWords(String filePath) throws IOException {
        ArrayList<String> result = new ArrayList<>();

        FileInputStream fstream = new FileInputStream(filePath);
        DataInputStream in = new DataInputStream(fstream);
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        String data;
        while ((data = br.readLine()) != null)   {
            String[] tmp = data.split(" ");

//            Collections.addAll(result, tmp);
            for (String s : tmp) {
                if (s.trim().length() > 0) {
                    result.add(s);
                }
            }


        }

        return result;
    }

    public static ArrayList<String> getWordsAndShuffle(String filePath) throws IOException {
        ArrayList<String> result = new ArrayList<>();

        FileInputStream fstream = new FileInputStream(filePath);
        DataInputStream in = new DataInputStream(fstream);
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        String data;
        while ((data = br.readLine()) != null)   {
            String[] tmp = data.split(" ");

            for (String s : tmp) {
                if (s.trim().length() > 0) {
                    result.add(shuffle(s));
                }
            }

//            Collections.addAll(result, tmp);
        }

        return result;
    }


    public static void testPerformanceIsPalindromeI(ArrayList<String> words, int max) {
        long palindromeCount = 0;
        long totalLocalElapsedTime = 0;


//        ArrayList<String> localWords = new ArrayList<>();
//        for (String word : words) {
//            String clone = new String(word);
//            localWords.add(clone);
////            localWords.add(word);
//        }

        ArrayList<String> localWords = words;

        long globalStartTime = System.nanoTime();

        for (int i = 0; i < max; ++i) {

            for (String word : localWords) {
//                String localWord = cloneString(word);

                long localStartTime = System.nanoTime();
                if (isPalindromeI(word)) {
                    ++palindromeCount;
                }
                long localElapsedTime = System.nanoTime() - localStartTime;
                totalLocalElapsedTime += localElapsedTime;
            }
        }
        long globalElapsedTime = System.nanoTime() - globalStartTime;

        System.out.println("palindromeCount: " + palindromeCount);
        System.out.println("testPerformanceIsPalindromeI globalElapsedTime:     " + globalElapsedTime     + " ns - " + ((double)globalElapsedTime / 1000000000.0) + " secs.");
        System.out.println("testPerformanceIsPalindromeI totalLocalElapsedTime: " + totalLocalElapsedTime + " ns - " + ((double)totalLocalElapsedTime / 1000000000.0) + " secs.");
    }

    public static void testPerformanceIsPalindromeN(ArrayList<String> words, int max) {
        long palindromeCount = 0;
        long totalLocalElapsedTime = 0;


//        ArrayList<String> localWords = new ArrayList<>();
//        for (String word : words) {
//            String clone = new String(word);
//            localWords.add(clone);
////            localWords.add(word);
//        }

        ArrayList<String> localWords = words;

        long globalStartTime = System.nanoTime();

        for (int i = 0; i < max; ++i) {

            for (String word : localWords) {
//                String localWord = cloneString(word);

                long localStartTime = System.nanoTime();
                if (isPalindromeN(word)) {
                    ++palindromeCount;
                }
                long localElapsedTime = System.nanoTime() - localStartTime;
                totalLocalElapsedTime += localElapsedTime;
            }
        }
        long globalElapsedTime = System.nanoTime() - globalStartTime;

        System.out.println("palindromeCount: " + palindromeCount);
        System.out.println("testPerformanceIsPalindromeN globalElapsedTime:     " + globalElapsedTime     + " ns - " + ((double)globalElapsedTime / 1000000000.0) + " secs.");
        System.out.println("testPerformanceIsPalindromeN totalLocalElapsedTime: " + totalLocalElapsedTime + " ns - " + ((double)totalLocalElapsedTime / 1000000000.0) + " secs.");
    }

    public static void testPerformanceIsPalindromeO(ArrayList<String> words, int max) {
        long palindromeCount = 0;
        long totalLocalElapsedTime = 0;


//        ArrayList<String> localWords = new ArrayList<>();
//        for (String word : words) {
//            String clone = new String(word);
//            localWords.add(clone);
////            localWords.add(word);
//        }

        ArrayList<String> localWords = words;

        long globalStartTime = System.nanoTime();

        for (int i = 0; i < max; ++i) {

            for (String word : localWords) {
//                String localWord = cloneString(word);

                long localStartTime = System.nanoTime();
                if (isPalindromeO(word)) {
                    ++palindromeCount;
                }
                long localElapsedTime = System.nanoTime() - localStartTime;
                totalLocalElapsedTime += localElapsedTime;
            }
        }
        long globalElapsedTime = System.nanoTime() - globalStartTime;

        System.out.println("palindromeCount: " + palindromeCount);
        System.out.println("testPerformanceIsPalindromeO globalElapsedTime:     " + globalElapsedTime + " ns - " + ((double) globalElapsedTime / 1000000000.0) + " secs.");
        System.out.println("testPerformanceIsPalindromeO totalLocalElapsedTime: " + totalLocalElapsedTime + " ns - " + ((double) totalLocalElapsedTime / 1000000000.0) + " secs.");
    }



    public static void testPerformanceNumberOfPalindromesN(String str, int max) {
        long palindromeCount = 0;
        long globalStartTime = System.nanoTime();
        for (int i = 0; i < max; ++i) {
            palindromeCount += numberOfPalindromesN(str);
        }
        long globalElapsedTime = System.nanoTime() - globalStartTime;

        System.out.println("palindromeCount: " + palindromeCount);
        System.out.println("testPerformanceNumberOfPalindromesN: " + globalElapsedTime     + " ns - " + ((double)globalElapsedTime / 1000000000.0) + " secs.");
    }

    public static void testPerformanceNumberOfPalindromesO(String str, int max) {
        long palindromeCount = 0;
        long globalStartTime = System.nanoTime();
        for (int i = 0; i < max; ++i) {
            palindromeCount += numberOfPalindromesO(str);
        }
        long globalElapsedTime = System.nanoTime() - globalStartTime;

        System.out.println("palindromeCount: " + palindromeCount);
        System.out.println("testPerformanceNumberOfPalindromesO: " + globalElapsedTime     + " ns - " + ((double)globalElapsedTime / 1000000000.0) + " secs.");
    }

    public static void testPerformanceNumberOfPalindromesI(String str, int max) {
        long palindromeCount = 0;
        long globalStartTime = System.nanoTime();
        for (int i = 0; i < max; ++i) {
            palindromeCount += numberOfPalindromesI(str);
        }
        long globalElapsedTime = System.nanoTime() - globalStartTime;

        System.out.println("palindromeCount: " + palindromeCount);
        System.out.println("testPerformanceNumberOfPalindromesI: " + globalElapsedTime     + " ns - " + ((double)globalElapsedTime / 1000000000.0) + " secs.");
    }

    public static void testPerformanceNumberOfPalindromesU(String str, int max) {
        long palindromeCount = 0;
        long globalStartTime = System.nanoTime();
        for (int i = 0; i < max; ++i) {
            palindromeCount += numberOfPalindromesU(str);
        }
        long globalElapsedTime = System.nanoTime() - globalStartTime;

        System.out.println("palindromeCount: " + palindromeCount);
        System.out.println("testPerformanceNumberOfPalindromesU: " + globalElapsedTime     + " ns - " + ((double)globalElapsedTime / 1000000000.0) + " secs.");
    }



    public static void bench(String benchName, String filePath, int garbageThreads, int max) throws IOException, InterruptedException {

        System.out.println("Running " + benchName + "...");
        System.out.println("filePath:       " + filePath);
        System.out.println("garbageThreads: " + garbageThreads);
        System.out.println("max:            " + max);

        System.out.println("reading Words ...");
        ArrayList<String> words = getWords(filePath);
        System.out.println("Words readed. Words: " + words.size());

        ArrayList<Thread> threads = new ArrayList<>(garbageThreads);
        for (int i = 0; i < garbageThreads; ++i) {
            Thread t = new Thread(new GarbageCreator());
            threads.add(t);
            t.start();
        }

        if (garbageThreads > 0) {
            System.out.println("Waiting 2 seconds before start...");
            Thread.sleep(2000);
        }

        System.out.println("Real work starts now...");

        testPerformanceIsPalindromeN(words, max);
        testPerformanceIsPalindromeO(words, max);
        testPerformanceIsPalindromeI(words, max);

        for (Thread t : threads) {
            t.stop();
        }

        System.out.println("objectsCreated:       " + GarbageCreator.objectsCreated);
        System.out.println("objectsCollected:     " + Garbage.objectsCollected);



    }

    public static void bench1() throws IOException, InterruptedException {
        bench("Bench1", "D:\\Development\\componentsprogramming\\palindrome\\part1\\java\\shakespe.are.txt", 0, 100);
    }

    public static void bench2() throws IOException, InterruptedException {
        bench("Bench2", "D:\\Development\\componentsprogramming\\palindrome\\part1\\java\\shakespe.are.txt", 1, 100);
    }

    public static void bench3() throws IOException, InterruptedException {
        bench("Bench3", "D:\\Development\\componentsprogramming\\palindrome\\part1\\java\\big_words.txt", 0, 100);
    }

    public static void bench4() throws IOException, InterruptedException {
        bench("Bench4", "D:\\Development\\componentsprogramming\\palindrome\\part1\\java\\big_words.txt", 1, 100);
    }

    public static void bench5() throws IOException, InterruptedException {
        bench("Bench5", "D:\\Development\\componentsprogramming\\palindrome\\part1\\java\\big_palin.txt", 0, 100);
    }

    public static void bench6() throws IOException, InterruptedException {
        bench("Bench6", "D:\\Development\\componentsprogramming\\palindrome\\part1\\java\\big_palin.txt", 1, 100);
    }

    public static void bench7() throws IOException, InterruptedException {
        bench("Bench7", "D:\\Development\\componentsprogramming\\palindrome\\part1\\java\\big_palin2.txt", 0, 100);
    }

    public static void bench8() throws IOException, InterruptedException {
        bench("Bench8", "D:\\Development\\componentsprogramming\\palindrome\\part1\\java\\big_palin2.txt", 1, 100);
    }

    public static void bench9() throws IOException, InterruptedException {
        bench("Bench9", "D:\\Development\\componentsprogramming\\palindrome\\part1\\java\\shakespe.are_shuffled.txt", 0, 100);
    }

    public static void bench10() throws IOException, InterruptedException {
        bench("Bench10", "D:\\Development\\componentsprogramming\\palindrome\\part1\\java\\shakespe.are_shuffled.txt", 1, 100);
    }


    public static String getPalindrome() {

        // http://norvig.com/palindrome.html
        // http://norvig.com/palindrome-a.html

        String phrase = "A man, a plan, a casa, a bait, a lag, a malt, an ami, an abr, a bayard, a liang, an apa, a jager, an adder, a gaw, a nut, an oca, a net, a caps, a rail, a karat, an amalaka, an agron, a brev, a kit, a ballet, an eel, a bar, a lama, a dan, a patina, a grama, a tin, a manak, a japer, a pupa, an oka, a lev, a trad, a cab, an ala, a papaya, a loma, a cam, a catalpa, a mon, a kor, a tam, a haloid, a regal, a lub, a war, a peen, a stob, a gad, a spin, a ward, a slag, a toom, a we'd, a pos, a marg, a cedar, a daraf, a wat, a dater, a carapa, a lot, a tennis, a laic, a farer, a dame, a haemin, an agal, a tsar, a misaim, an enate, a halo, a garrot, a gip, a rom, a lca, an elem, a yell, a ganoin, a waver, a boma, a ball, a waxer, a tid, an agr, an app, a y-axis, a lion, a lack, a burg, a motmot, a rata, a vil, a satrap, a mart, a rank, a lex, an annot, a pol, a tael, a drub, a nome, a dale, a batwoman, a mara, a lob, a tarp, a res, a drawer, a rot, a doh, a nib, a saw, a harem, a gater, an imam, a llama, a let, a bel, a hamal, a gasser, a cadi, a ramrod, a saga, a bass, a cad, a ya-ta-ta, a flan, a cig, a malam, an anus, a keep, a cuda, a crum, an alapa, a doc, a nett, a laggen, a loofa, a cola, a roman, a vac, a snug, a mass, an ass, a tarpan, a frt, a neb, a reel, a corp, a yob, an ana, a but, a tpd, a pant, a cate, an alb, a napper, a gum, a knot, a daud, a pipa, a ruse, a call, a haar, a matador, a bot, a mod, a cor, an aug, a dop, a bud, a buran, a sika, a nork, a yoga, a kip, a rood, a tanga, a pap, a loop, a rein, a pat, an anadem, a leman, a brocatel, a says, a jad, a nomad, a tau, a nunatak, a drab, a gob, an amt, an andron, an orc, a maim, a kaiak, a saber, a puca, a rab, a rabal, a kabala, a rem, a canoe, an aid, a water, a minah, a yarak, an okay, a redan, a gala, a liza, a coir, a car, a vav, a hail, a guna, a min, an aroma, a mix, a manikin, a capita, a cap, a tsi, a waw, a spira, a rub, an att, a matsu, a hat, a fan, a taj, a ray, a jar, a lah, a datum, a gayal, a bus, a coss, an alias, a lin, a gata, a cabana, a nail, a nod, a vas, a lam, a recall, a nasal, a bat, an aba, a rec, a nim, a gauss, a baba, an ign, an agura, a gleba, a lune, a pastel, a yew, an inner, an exon, a moor, a mir, a pas, a dir, a canalis, a bason, a beg, a kop, a yam, a fader, a citer, a pan, a lunarian, a mot, an alif, a yes, a jaw, a pay, a salad, a poll, a waggon, an ore, an asp, a tabor, a cariama, a kab, a kabob, a lac, a deb, a mun, a dalasi, a daff, a raki, a halal, a sanies, a cast, a hwan, a cay, a dad, an ogam, a ma'am, a japan, a vakeel, a fac, a duel, a sial, a patin, a magot, a roll, a pain, a rall, a fats, a bap, a cerat, a call-up, an abb, a radiov, a gid, a loot, a god, a tenner, an eta, a gar, a grad, a dap, a dirt, a hair, a parr, an agon, a dual, a nonet, a mural, a soda, a teff, a tal, an omasum, a tug, a vat, a doom, a deil, a str, a dam, a gaff, a camoca, a mud, a flow, a fed, a gpm, a tod, a fcp, a reflet, a hay, a nav, a lulab, a tail, a maniac, an ikan, a maid, a salep, a lass, a pall, a gab, a watt, a bay, a map, a lair, a sax, a mar, a madam, a palet, a ramon, a won, a wet, a fen, a nipa, a dBm, a lai, a bag, a haeres, a bun, a ratton, a tana, a sem, a gtc, a giron, a bros, a navar, a carotin, a jab, a dayan, a lever, a fun, a macoma, an iter, a nies, a dak, a yaw, a sera, a tef, a nek, a xat, a boob, a haler, a cir, a daw, a camass, a topaz, a gator, a taw, a lapser, a mut, a biga, a nag, an atlas, a bad, a doodah, a manas, a puna, a fut, a sima, a gen, a facsim, a liard, a gym, a spot, a sex, a nae, a wap, a zax, a way, a tapis, an ord, a habu, a datura, a mel, a kadi, a qat, a mas, a banana, a cran, a bird, a pus, a drain, a lacer, a valor, a pav, a lad, a wamus, a garg, a tamis, a kaon, a tort, a leg, a baht, a lapin, a ref, a wall, a mac, a samaj, a boar, a sunn, a pappus, an aced, an ann, a manor, a barege, a jai, an alap, an acad, a fakeer, a remit, a get, a toil, a gat, a local, a baff, a wain, an akela, an uta, a slab, a rattan, a lyc, an atoll, a banian, a retem, a dorp, a poort, a vug, a supr, a canon, a far, a fanatic, a dep, a caid, a qaf, a kas, a carfax, a patness, a nabob, a catnip, a minima, a must, a sal, a caracara, a manioc, a til, a lip, a rag, an amir, a hareem, an arcs, a teel, a cram, a yawn, a main, a ban, a rasa, a lud, a ragi, a tapir, a top, a dil, a lat, a razz, a jag, a sass, a jalor, a calix, an asb, an ayre, a nap, a tannin, a garb, a sports, a gem, a reed, an esu, a hap, a raglan, a sis, a baronet, a snit, a madras, a plug, a gula, a ros, a scf, a gelder, a terr, a gag, a fay, a fag, a garret, a redleg, a fcs, a sora, a lug, a gulp, a sard, a matins, a tenor, a basis, an alg, a rap, a hausen, a deer, a meg, a strops, a brag, an inn, a tap, an aery, an abs, an axil, a carol, a jass, a sag, a jazz, a ratal, a lid, a pot, a rip, a taig, a radula, a saran, a bani, a manway, a marc, a leet, a scr, an ameer, a harim, an agar, a pil, a lit, a coin, a maar, a caracal, a satsuma, a minim, a pint, a cabob, an assent, a pax, a fracas, a kaf, a qadi, a caped, a cit, an afar, a fanon, a carpus, a guv, a troop, a prod, a meter, an ain, a ballot, an acyl, an attar, a balsa, a tuna, a lek, an ani, a waff, a bal, a colat, a galiot, a teg, a timer, a reek, a fad, a canap, a lanai, a jaeger, a baron, a mannan, a decan, a supp, a pannus, a raob, a jam, a sac, a mall, a wafer, a nip, a lath, a bagel, a trot, an oak, a sim, a tagrag, a sum, a wad, a lav, a parol, a varec, a laniard, a sup, a drib, an arca, an anabas, a mat, a qaid, a kalema, a rut, a daub, a hadron, a sip, a tay, a wax, a zap, a wae, an axes, a tops, a myg, a drail, a misc, a fanega, a mis, a tufa, an upas, an amah, a doodad, a basalt, a nagana, a gib, a tum, a resp, a law, a tarot, a gaz, a potass, a macaw, a daric, a rel, a haboob, a tax, a ken, a feta, a resaw, a yak, a dasein, a retina, a moc, a manuf, a revel, a nay, a dab, a janitor, a caravan, a sorb, an orig, a ctg, a mesa, a nat, an ottar, a nub, a sere, a hag, a bai, a lambda, a pin, a nef, a tew, a now, a nom, a ratel, a pam, a damar, a max, a sari, a lap, a may, a batt, a wab, a gall, a pass, a lapel, a sadi, a manakin, a cain, a mali, a tab, a lulav, an ayah, a telfer, a pcf, a dot, a mpg, a def, a wolf, a duma, a com, a caff, a gam, a darts, a lied, a mood, a tav, a gut, a mus, a monal, a taffeta, a dos-, a larum, a tenon, a laud, a nog, an arr, a pariah, a trid, a pad, a darg, a raga, a ten, a rennet, a dog, a tool, a dig, a void, a rabban, a pull, a cat, a recap, a bast, a fall, a rani, a pallor, a tog, a manit, a palais, a leud, a caf, a leek, a van, a pajama, a mam, a gonad, a day, a can, a what's, a casein, a salal, a haik, a raff, a dais, a ladanum, a bed, a cal, a bob, a kabaka, a mair, a carob, a taps, an aeron, a nogg, a wallop, a dal, a say', a paw, a jasey, a fil, an atom, an air, an ulan, a paretic, a red, a fam, a yapok, a geb, a nos, a basil, an acarid, a sap, a rim, a room, an oxen, a rennin, a wey, a let's, a paenula, a belga, a rug, an angina, a babassu, a gamin, a cera, a banat, a balas, an all, a ceramal, a sav, a don, a liana, an abaca, a tag, a nil, a sail, an assoc, a sub, a lay, a gamut, a dah, a lar, a jay, a raj, a tan, a fat, a haust, a matt, an abura, a ripsaw, a waist, a paca, a tip, a canikin, a maxima, a mor, an anima, an ugali, a hav, a var, a carioca, a zila, a lagan, a deray, a konak, a rayah, an imaret, a wadi, an aeon, a camera, a lab, a kal, a barabara, a cup, a reb, a sakai, a kami, a macron, an ordn, an atman, a bog, a bard, a kat, an unau, a tad, a monad, a jasy, a salet, a corban, a melamed, an anat, a panier, a pool, a papa, a gnat, a door, a pika, a goy, a krona, a kisan, a rub-a-dub, a pod, a guan, a roc, a dom, a tob, a rod, a tamara, a hall, a caesura, a pip, a duad, a tonk, a mug, a repp, an abl, an aet, a catnap, a dpt, a tuba, a nan, a boy, a proc, a leer, a ben, a trf, an apr, a tass, an assam, a guns, a cav, an amora, a loca, a fool, an egg, a latten, a coda, a pal, an amurca, a duc, a peek, a sun, an amal, a magic, an alfa, a tat, a yad, a cassaba, a gas, a dorm, a raid, a caress, a gal, a mahaleb, a tela, a mallam, a minaret, a gamer, a haw, a sabin, a hod, a tor, a reward, a ser, a prat, a bola, a ram, an amowt, a bael, a daemon, a burd, a leat, a lop, a tonn, an axel, a knar, a tram, a part, a saliva, a tar, a tom-tom, a grub, a kcal, an oil, a six, a yapp, an arg, an adit, a rex, a wallaba, a mob, a rev, a wanion, a galley, a melena, a clamor, a pig, a torr, a gaol, a haet, an enami, a simar, a stalag, an anime, a haem, a darer, a facial, a sinnet, a tola, a par, a caret, a dat, a waf, a radar, a decagram, a sop, a dew, a moot, a gals, a draw, an ips, a dag, a bots, a neep, a raw, a bul, a lager, a diol, a ham, a tarok, a noma, a plat, a camaca, a mola, a yap, a paal, an abac, a dart, a vela, a kona, a pup, a rep, a jak, an amanita, a marga, a nit, a panada, a malar, a baleen, a tell, a batik, a verb, an organa, a kalam, an atar, a kali, a rasp, a catena, a con, a tun, a wag, a redd, an areg, a jaap, an agnail, a dray, a barb, an aim, a natl, a mag, a lati, a baas, a canal, Panama!".toLowerCase();
        phrase = phrase.replace("!", "");
        phrase = phrase.replace(",", "");
        phrase = phrase.replace("-", "");
        phrase = phrase.replace("'", "");
        phrase = phrase.replace(" ", "");
        return phrase;
    }

    public static void numberOfPalindromesBench() {

        String phrase = getPalindrome();

        System.out.println(phrase);
        System.out.println(isPalindromeI(phrase));

        testPerformanceNumberOfPalindromesN(phrase, 1);
        testPerformanceNumberOfPalindromesO(phrase, 1);
        testPerformanceNumberOfPalindromesI(phrase, 1);
        testPerformanceNumberOfPalindromesU(phrase, 1);

    }

    public static void numberOfPalindromesDoubleBench() {
        String phrase = getPalindrome();
        phrase += phrase;

        System.out.println(phrase);
        System.out.println(isPalindromeI(phrase));

        testPerformanceNumberOfPalindromesN(phrase, 1);
        testPerformanceNumberOfPalindromesO(phrase, 1);
        testPerformanceNumberOfPalindromesI(phrase, 1);
    }

    public static void main(String[] args) throws IOException, InterruptedException {



//        bench1();
//        bench2();
//        bench3();
//        bench4();
//        bench5();
//        bench6();
//        bench7();
//        bench8();
//        bench9();
//        bench10();


        numberOfPalindromesBench();


//        String phrase = getPalindrome();
////        boolean x = isPalindromeU(phrase.toCharArray(), 0, phrase.length());
//        boolean x = isPalindromeU(phrase);
//        System.out.println(x);



//        numberOfPalindromesU("123");

        //-----------------------------------------------------------
        //Benchmarking with DNA Palindromes
        //-----------------------------------------------------------



////        Collections.shuffle(wordsShuffled);
    }
}


/*

 4,24
 3,62
 7,00
17,24
36,58
 2,06
 4,97
 2,60
 5,54
 2,94
 6,76



------------------------------------------------------------------------------------------
NumberOfPalindromesBench (Comparisson with the Array version)
------------------------------------------------------------------------------------------


testPerformanceNumberOfPalindromesN:  8847536678 ns -  8.847536678 secs.
testPerformanceNumberOfPalindromesO:  8475452735 ns -  8.475452735 secs.
testPerformanceNumberOfPalindromesI: 35259630525 ns - 35.259630525 secs.
testPerformanceNumberOfPalindromesU:    60165604 ns -  0.060165604 secs.

IsPalindromeI es 4,160206142073424x mas lento que IsPalindromeN
IsPalindromeI es 4,16x              mas lento que IsPalindromeN

IsPalindromeI es 586,0429910252376x mas lento que IsPalindromeU
IsPalindromeI es 586,04x            mas lento que IsPalindromeU



------------------------------------------------------------------------------------------
NumberOfPalindromesBench

testPerformanceNumberOfPalindromesN:      8492989490 ns -  8.49298949 secs.
testPerformanceNumberOfPalindromesO:      8276807197 ns -  8.276807197 secs.
testPerformanceNumberOfPalindromesI:     35162897620 ns - 35.16289762 secs.

IsPalindromeI es 4,248364953184495x mas lento que IsPalindromeN
IsPalindromeI es 4,24x              mas lento que IsPalindromeN

------------------------------------------------------------------------------------------
NumberOfPalindromesDoubleBench


testPerformanceNumberOfPalindromesN:  65116003791 ns -  65.116003791 secs.
testPerformanceNumberOfPalindromesO:  63870627358 ns -  63.870627358 secs.
testPerformanceNumberOfPalindromesI: 276127342574 ns - 276.127342574 secs.

NUMEROS?

------------------------------------------------------------------------------------------
Running Bench1...
filePath:       shakespe.are.txt
garbageThreads: 0
max:            100
testPerformanceIsPalindromeN globalElapsedTime:     4890310929 ns - 4.890310929 secs.
testPerformanceIsPalindromeN totalLocalElapsedTime: 1325268632 ns - 1.325268632 secs.
testPerformanceIsPalindromeO globalElapsedTime:     4621763417 ns - 4.621763417 secs.
testPerformanceIsPalindromeO totalLocalElapsedTime: 1377614855 ns - 1.377614855 secs.
testPerformanceIsPalindromeI globalElapsedTime:     8364931024 ns - 8.364931024 secs.
testPerformanceIsPalindromeI totalLocalElapsedTime: 4798092043 ns - 4.798092043 secs.


IsPalindromeI es 3,620467524202293x mas lento que IsPalindromeN
IsPalindromeI es 3,62x              mas lento que IsPalindromeN



------------------------------------------------------------------------------------------
Running Bench2...
filePath:       shakespe.are.txt
garbageThreads: 1
max:            100
testPerformanceIsPalindromeN globalElapsedTime:     10839523321 ns - 10.839523321 secs.
testPerformanceIsPalindromeN totalLocalElapsedTime:  2089672765 ns -  2.089672765 secs.
testPerformanceIsPalindromeO globalElapsedTime:     10844654726 ns - 10.844654726 secs.
testPerformanceIsPalindromeO totalLocalElapsedTime:  1990844634 ns -  1.990844634 secs.
testPerformanceIsPalindromeI globalElapsedTime:     24127784340 ns - 24.12778434  secs.
testPerformanceIsPalindromeI totalLocalElapsedTime: 13923980065 ns - 13.923980065 secs.


IsPalindromeI es 6,994006376592017x mas lento que IsPalindromeO
IsPalindromeI es 7,00x              mas lento que IsPalindromeO


------------------------------------------------------------------------------------------
Running Bench3...
filePath:       big_words.txt
garbageThreads: 0
max:            100
testPerformanceIsPalindromeN globalElapsedTime:      984165303 ns - 0.984165303 secs.
testPerformanceIsPalindromeN totalLocalElapsedTime:  474348072 ns - 0.474348072 secs.
testPerformanceIsPalindromeO globalElapsedTime:      962673616 ns - 0.962673616 secs.
testPerformanceIsPalindromeO totalLocalElapsedTime:  427857895 ns - 0.427857895 secs.
testPerformanceIsPalindromeI globalElapsedTime:     7951520007 ns - 7.951520007 secs.
testPerformanceIsPalindromeI totalLocalElapsedTime: 7377043086 ns - 7.377043086 secs.


IsPalindromeI es 17,24180661899437x mas lento que IsPalindromeO
IsPalindromeI es 17,24x             mas lento que IsPalindromeO

------------------------------------------------------------------------------------------
Running Bench4...
filePath:       big_words.txt
garbageThreads: 1
max:            100
testPerformanceIsPalindromeN globalElapsedTime:      3756066468 ns -  3.756066468 secs.
testPerformanceIsPalindromeN totalLocalElapsedTime:   626345121 ns -  0.626345121 secs.
testPerformanceIsPalindromeO globalElapsedTime:      4043855785 ns -  4.043855785 secs.
testPerformanceIsPalindromeO totalLocalElapsedTime:   528332552 ns -  0.528332552 secs.
testPerformanceIsPalindromeI globalElapsedTime:     31768068113 ns - 31.768068113 secs.
testPerformanceIsPalindromeI totalLocalElapsedTime: 19330586626 ns - 19.330586626 secs.

IsPalindromeI es 36,58791523033016x mas lento que IsPalindromeO
IsPalindromeI es 36,58x             mas lento que IsPalindromeO



------------------------------------------------------------------------------------------
Running Bench5...
filePath:       big_palin.txt
garbageThreads: 0
max:            100
testPerformanceIsPalindromeN globalElapsedTime:     10357708825 ns - 10.357708825 secs.
testPerformanceIsPalindromeN totalLocalElapsedTime: 10005128204 ns - 10.005128204 secs.
testPerformanceIsPalindromeO globalElapsedTime:      6944588611 ns -  6.944588611 secs.
testPerformanceIsPalindromeO totalLocalElapsedTime:  6606331327 ns -  6.606331327 secs.
testPerformanceIsPalindromeI globalElapsedTime:     14851472793 ns - 14.851472793 secs.
testPerformanceIsPalindromeI totalLocalElapsedTime: 13615714833 ns - 13.615714833 secs.

IsPalindromeI es 2,061009985580458x mas lento que IsPalindromeO
IsPalindromeI es 2,06x              mas lento que IsPalindromeO


------------------------------------------------------------------------------------------
Running Bench6...
filePath:       big_palin.txt
garbageThreads: 1
max:            100
testPerformanceIsPalindromeN globalElapsedTime:     38172734061 ns - 38.172734061 secs.
testPerformanceIsPalindromeN totalLocalElapsedTime: 11260911872 ns - 11.260911872 secs.
testPerformanceIsPalindromeO globalElapsedTime:     26788582614 ns - 26.788582614 secs.
testPerformanceIsPalindromeO totalLocalElapsedTime:  7921812023 ns -  7.921812023 secs.
testPerformanceIsPalindromeI globalElapsedTime:     60507487343 ns - 60.507487343 secs.
testPerformanceIsPalindromeI totalLocalElapsedTime: 39415932596 ns - 39.415932596 secs.

IsPalindromeI es 4,975620789986019x mas lento que IsPalindromeO
IsPalindromeI es 4,97x              mas lento que IsPalindromeO


------------------------------------------------------------------------------------------
Running Bench7...
filePath:       big_palin2.txt
garbageThreads: 0
max:            100
testPerformanceIsPalindromeN globalElapsedTime:     3272109003 ns - 3.272109003 secs.
testPerformanceIsPalindromeN totalLocalElapsedTime: 2692569985 ns - 2.692569985 secs.
testPerformanceIsPalindromeO globalElapsedTime:     2431603791 ns - 2.431603791 secs.
testPerformanceIsPalindromeO totalLocalElapsedTime: 1847667851 ns - 1.847667851 secs.
testPerformanceIsPalindromeI globalElapsedTime:     5404027890 ns - 5.40402789  secs.
testPerformanceIsPalindromeI totalLocalElapsedTime: 4808025249 ns - 4.808025249 secs.

IsPalindromeI es 2,602212971556434x mas lento que IsPalindromeO
IsPalindromeI es 2,60x              mas lento que IsPalindromeO


------------------------------------------------------------------------------------------
Running Bench8...
filePath:       big_palin2.txt
garbageThreads: 1
max:            100
testPerformanceIsPalindromeN globalElapsedTime:     12010477919 ns - 12.010477919 secs.
testPerformanceIsPalindromeN totalLocalElapsedTime:  3189806103 ns -  3.189806103 secs.
testPerformanceIsPalindromeO globalElapsedTime:      9007972302 ns -  9.007972302 secs.
testPerformanceIsPalindromeO totalLocalElapsedTime:  2328281368 ns -  2.328281368 secs.
testPerformanceIsPalindromeI globalElapsedTime:     19960467356 ns - 19.960467356 secs.
testPerformanceIsPalindromeI totalLocalElapsedTime: 12918209587 ns - 12.918209587 secs.

IsPalindromeI es 5,548388508600564x mas lento que IsPalindromeO
IsPalindromeI es 5,54x              mas lento que IsPalindromeO


------------------------------------------------------------------------------------------
Running Bench9...
filePath:       shakespe.are_shuffled.txt
garbageThreads: 0
max:            100
testPerformanceIsPalindromeN globalElapsedTime:     2746986118 ns - 2.746986118 secs.
testPerformanceIsPalindromeN totalLocalElapsedTime: 1754937699 ns - 1.754937699 secs.
testPerformanceIsPalindromeO globalElapsedTime:     2786426816 ns - 2.786426816 secs.
testPerformanceIsPalindromeO totalLocalElapsedTime: 1657318899 ns - 1.657318899 secs.
testPerformanceIsPalindromeI globalElapsedTime:     5961645111 ns - 5.961645111 secs.
testPerformanceIsPalindromeI totalLocalElapsedTime: 4875846229 ns - 4.875846229 secs.

IsPalindromeI es 2,942008464358916x mas lento que IsPalindromeO
IsPalindromeI es 2,94x              mas lento que IsPalindromeO


------------------------------------------------------------------------------------------
Running Bench10...
filePath:       shakespe.are_shuffled.txt
garbageThreads: 1
max:            100
testPerformanceIsPalindromeN globalElapsedTime:     10744360758 ns - 10.744360758 secs.
testPerformanceIsPalindromeN totalLocalElapsedTime:  2053862968 ns -  2.053862968 secs.
testPerformanceIsPalindromeO globalElapsedTime:     10581596992 ns - 10.581596992 secs.
testPerformanceIsPalindromeO totalLocalElapsedTime:  1968164282 ns -  1.968164282 secs.
testPerformanceIsPalindromeI globalElapsedTime:     23717037598 ns - 23.717037598 secs.
testPerformanceIsPalindromeI totalLocalElapsedTime: 13306474571 ns - 13.306474571 secs.

IsPalindromeI es 6,760855632172274x mas lento que IsPalindromeO
IsPalindromeI es 6,76x              mas lento que IsPalindromeO


* */