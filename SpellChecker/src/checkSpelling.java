import java.util.Arrays;
import java.util.Hashtable;

public class checkSpelling {

    private String word;
    public Hashtable<String, Boolean>dictionary;

    public void spellChecker(String w, Hashtable<String, Boolean>dict){
        this.word = w;
        this.dictionary = dict;
    }
    public static String findCorrections(String word, Hashtable<String, Boolean>dictionary) {
        if(!(inDictionary(word, dictionary))){
            String sub = substitution(word, dictionary);
            String omi = omission(word, dictionary);
            String ins = insertion(word, dictionary);
            String rev = reversal(word, dictionary);
            if(sub!="none"){
                return sub;
            }
            else if(omi!="none"){
                return omi;
            }
            else if(ins!="none"){
                return ins;
            }
            else if(rev!="none"){
                return rev;
            }
        }
        return "none";
    }
    
    private static boolean inDictionary(String word, Hashtable<String, Boolean> dictionary){
        if(dictionary.containsKey(word.toLowerCase())){
            return true;
        }
        else{
            return false;
        }
    }
    
    static String substitution(String word, Hashtable<String, Boolean> dictionary) {
        char alphabet[]= {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'}; // create alphabet array
        char[] arr_word = word.toCharArray(); 
        for(int k=0;k<arr_word.length;k++){
            char[] edited_arr_word = Arrays.copyOf(arr_word, arr_word.length); 
            for(int i=0;i<alphabet.length;i++){ 
                edited_arr_word[k] = alphabet[i]; 
                String str_word = String.valueOf(edited_arr_word); 
                str_word.toLowerCase();
                if(dictionary.containsKey(str_word)){ 
                    return str_word; 
                }
            }
        }
        return "none"; 
    }

    static String omission(String word, Hashtable<String, Boolean> dictionary) {
        char[] arr_word = word.toCharArray();
        for(int k=0;k<arr_word.length;k++){ 
            char[] edited_arr_word = new char[arr_word.length-1]; 
            int j = 0;
            for(int i=0;i<arr_word.length;i++){
                if(i != k){
                    edited_arr_word[j++] = arr_word[i];
                }
            }
            String str_word = String.valueOf(edited_arr_word).toLowerCase().trim(); 
            if(dictionary.containsKey(str_word)) { 
                return str_word;
            }
        }
        return "none"; 
    }

    static String insertion(String word, Hashtable<String, Boolean> dictionary) {
        char alphabet[] = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
        char[] arr_word = word.toCharArray();
        for (int i = 0; i < arr_word.length + 1; i++) {
            for (int j = 0; j < alphabet.length; j++) {
                char[] insert_arr_word = new char[arr_word.length + 1]; 
                for (int k = 0; k < i; k++) {
                    insert_arr_word[k] = arr_word[k]; 
                }
                insert_arr_word[i] = alphabet[j];
                for (int k = i; k < arr_word.length; k++) {
                    insert_arr_word[k + 1] = arr_word[k];
                }
                String str_word = String.valueOf(insert_arr_word).trim().toLowerCase();
                if (dictionary.containsKey(str_word)) {
                    return str_word;
                }
            }
        }
        return "none";
    }

    static String reversal(String word, Hashtable<String, Boolean> dictionary) {
        char[] arr_word = word.toCharArray();
        for(int i=0;i<((arr_word.length)-1);i++){ 
            char[] edited_arr_word = Arrays.copyOf(arr_word, arr_word.length); 
            char temp; 
            temp = edited_arr_word[i];
            edited_arr_word[i] = edited_arr_word[i+1];
            edited_arr_word[i+1] = temp;
            String str_word = String.valueOf(edited_arr_word);
            str_word.trim(); 
            str_word.toLowerCase();
            if (dictionary.containsKey(str_word)) {
                return str_word; 
            }

        }
        return "none";
    }
    
    

}