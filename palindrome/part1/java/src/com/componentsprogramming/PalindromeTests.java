package com.componentsprogramming;

public class PalindromeTests {
    public static boolean isPalindromeI(String str) {
        return str.equals(new StringBuilder(str).reverse().toString());
    }

    public static boolean isPalindromeN(String str) {
        int n = str.length();
        for (int i = 0; i < n; ++i) {
            if (str.charAt(i) != str.charAt(n-i-1)) return false;
        }
        return true;
    }

    public static boolean isPalindromeO(String str) {
        int n = str.length();

        for (int i = 0; i < n/2; ++i) {
            if (str.charAt(i) != str.charAt(n-i-1)) return false;
        }
        return true;
    }

//    public static boolean isPalindromeO(String str) {
//        int first = 0;
//        int last = str.length();
//
//        while ((first != last) && (first != --last)) {
//            if (str.charAt(first) != str.charAt(last)) return false;
//            ++first;
//        }
//        return true;
//    }

//    public static <T>  boolean isPalindromeU(T[] arr, int first, int last) {
    public static boolean isPalindromeU(char[] arr, int first, int last) {
        //precondition: [first, last) is a valid range over arr

        while ((first != last) && (first != --last)) {
            if (arr[first] != arr[last]) return false;
            ++first;
        }

        return true;
    }

    public static boolean isPalindromeU(String str) {
        return isPalindromeU(str.toCharArray(), 0, str.length());
    }

    public static boolean isPalindromeX(String str, int first, int last) {
        //precondition: [first, last) is a valid range over the internal array of str

        while ((first != last) && (first != --last)) {
            if (str.charAt(first) != str.charAt(last)) return false;
            ++first;
        }

        return true;
    }

    public static boolean isPalindromeX(String str) {
        return isPalindromeX(str, 0, str.length());
    }


    //------------------------------------------------------------------------

    public static int numberOfPalindromesN(String str) {
        int res = 0;
        int n = str.length();

        for (int i = 0; i < n; ++i) {
            for (int j = n; j > i; --j) {
                String x = str.substring(i, j);

                if (isPalindromeN(x)) {
                    ++res;
                }
            }
        }
        return res;
    }

    public static int numberOfPalindromesO(String str) {
        int res = 0;
        int n = str.length();

        for (int i = 0; i < n; ++i) {
            for (int j = n; j > i; --j) {
               String x = str.substring(i, j);

             if (isPalindromeO(x)) {
                 ++res;
             }

//               System.out.println(x);
            }
        }

        return res;
    }

    public static int numberOfPalindromesI(String str) {
        int res = 0;
        int n = str.length();

        for (int i = 0; i < n; ++i) {
            for (int j = n; j > i; --j) {
                String x = str.substring(i, j);

                if (isPalindromeI(x)) {
                    ++res;
                }

                //               System.out.println(x);
            }
        }

        return res;
    }

    public static int numberOfPalindromesU(String str) {
        int res = 0;
        int n = str.length();
        char[] arr = str.toCharArray();

        for (int i = 0; i < n; ++i) {
            for (int j = n; j > i; --j) {

//                for (int k = i; k < j; ++k) {
//                    System.out.print(arr[k]);
//                }
//                System.out.println();

                if (isPalindromeU(arr, i, j)) {
                    ++res;
                }
            }
        }

        return res;
    }
}

/*
// class version 52.0 (52)
// access flags 0x21
public class com/componentsprogramming/PalindromeTests {

  // compiled from: PalindromeTests.java

  // access flags 0x1
  public <init>()V
   L0
    LINENUMBER 3 L0
    ALOAD 0
    INVOKESPECIAL java/lang/Object.<init> ()V
    RETURN
   L1
    LOCALVARIABLE this Lcom/componentsprogramming/PalindromeTests; L0 L1 0
    MAXSTACK = 1
    MAXLOCALS = 1

  // access flags 0x9
  public static isPalindromeI(Ljava/lang/String;)Z
   L0
    LINENUMBER 5 L0
    ALOAD 0
    NEW java/lang/StringBuilder
    DUP
    ALOAD 0
    INVOKESPECIAL java/lang/StringBuilder.<init> (Ljava/lang/String;)V
    INVOKEVIRTUAL java/lang/StringBuilder.reverse ()Ljava/lang/StringBuilder;
    INVOKEVIRTUAL java/lang/StringBuilder.toString ()Ljava/lang/String;
    INVOKEVIRTUAL java/lang/String.equals (Ljava/lang/Object;)Z
    IRETURN
   L1
    LOCALVARIABLE str Ljava/lang/String; L0 L1 0
    MAXSTACK = 4
    MAXLOCALS = 1

  // access flags 0x9
  public static isPalindromeN(Ljava/lang/String;)Z
   L0
    LINENUMBER 10 L0
    ALOAD 0
    INVOKEVIRTUAL java/lang/String.length ()I
    ISTORE 1
   L1
    LINENUMBER 11 L1
    ICONST_0
    ISTORE 2
   L2
   FRAME APPEND [I I]
    ILOAD 2
    ILOAD 1
    IF_ICMPGE L3
   L4
    LINENUMBER 12 L4
    ALOAD 0
    ILOAD 2
    INVOKEVIRTUAL java/lang/String.charAt (I)C
    ALOAD 0
    ILOAD 1
    ILOAD 2
    ISUB
    ICONST_1
    ISUB
    INVOKEVIRTUAL java/lang/String.charAt (I)C
    IF_ICMPEQ L5
    ICONST_0
    IRETURN
   L5
    LINENUMBER 11 L5
   FRAME SAME
    IINC 2 1
    GOTO L2
   L3
    LINENUMBER 14 L3
   FRAME CHOP 1
    ICONST_1
    IRETURN
   L6
    LOCALVARIABLE i I L2 L3 2
    LOCALVARIABLE str Ljava/lang/String; L0 L6 0
    LOCALVARIABLE n I L1 L6 1
    MAXSTACK = 4
    MAXLOCALS = 3

  // access flags 0x9
  public static isPalindromeO(Ljava/lang/String;)Z
   L0
    LINENUMBER 18 L0
    ALOAD 0
    INVOKEVIRTUAL java/lang/String.length ()I
    ISTORE 1
   L1
    LINENUMBER 20 L1
    ICONST_0
    ISTORE 2
   L2
   FRAME APPEND [I I]
    ILOAD 2
    ILOAD 1
    ICONST_2
    IDIV
    IF_ICMPGE L3
   L4
    LINENUMBER 21 L4
    ALOAD 0
    ILOAD 2
    INVOKEVIRTUAL java/lang/String.charAt (I)C
    ALOAD 0
    ILOAD 1
    ILOAD 2
    ISUB
    ICONST_1
    ISUB
    INVOKEVIRTUAL java/lang/String.charAt (I)C
    IF_ICMPEQ L5
    ICONST_0
    IRETURN
   L5
    LINENUMBER 20 L5
   FRAME SAME
    IINC 2 1
    GOTO L2
   L3
    LINENUMBER 23 L3
   FRAME CHOP 1
    ICONST_1
    IRETURN
   L6
    LOCALVARIABLE i I L2 L3 2
    LOCALVARIABLE str Ljava/lang/String; L0 L6 0
    LOCALVARIABLE n I L1 L6 1
    MAXSTACK = 4
    MAXLOCALS = 3
}

*/