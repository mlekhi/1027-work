public class MyTests {
    public static void main(String[] strings) {
        Letter letter1 = new Letter('x');
        Letter letter2 = new Letter('x');
        test(letter1.equals(letter2));
        letter1.setCorrect();
        test(letter1.decorator().equals("!"));
        letter1.setUsed();
        test(letter1.decorator().equals("+"));
        letter1.setUnused();
        test(letter1.decorator().equals("-"));
        test(letter1.isUnused());

        Letter[] letters1 = Letter.fromString("abc");
        Letter[] letters2 = { new Letter('a'), new Letter('b'), new Letter('c') };
        test(java.util.Arrays.equals(letters1, letters2));

        Word word = new Word(Letter.fromString("hello"));
        test(word.toString().equals("Word:  h   e   l   l   o  "));

        ExtendedLetter el1 = new ExtendedLetter("@");
        ExtendedLetter el2 = new ExtendedLetter("world");
        test(!el1.equals(el2));
        el1 = new ExtendedLetter("world");
        test(el1.equals(el2));
        test(el1.toString().equals(" world "));

        word = new Word(ExtendedLetter.fromStrings(new String[] { "world", "burger" }, null));
        test(word.toString().equals("Word:  world   burger  "));
        word.labelWord(new Word(ExtendedLetter.fromStrings(new String[] { "world", "abc" }, null)));
        test(word.toString().equals("Word: !world! .burger. "));

        word = new Word(Letter.fromString("ggged"));
        test(!word.labelWord(new Word(Letter.fromString("jonah"))));
        test(word.labelWord(new Word(Letter.fromString("ggged"))));

        word = new Word(ExtendedLetter.fromStrings(new String[] { "hello", "world" }, new int[] { 1, 2 }));
        word.labelWord(new Word(ExtendedLetter.fromStrings(new String[] { "asdf" }, new int[] { 1 })));
        test(word.toString().equals("Word: .hello. -world- "));

        test(el1.decorator().equals(" "));
        el1 = new ExtendedLetter("abc", 1);
        el1.setUnused();
        test(!el1.equals(new ExtendedLetter("def", 2)));
        test(el1.toString().equals("-abc-"));

        test(!el1.equals(new ExtendedLetter("def", 1)));
        test(el1.toString().equals(".abc."));
        test(el1.equals(new ExtendedLetter("abc", 1)));
        test(el1.toString().equals(".abc."));

        System.out.println(passed + " / " + run);
    }

    private static int run = 0;
    private static int passed = 0;

    public static void test(boolean testStatus) {
        System.out.println("(" + run + ") " + (testStatus ? "passed" : "failed"));
        ++run;
        if (testStatus) {
            ++passed;
        }
    }
}
