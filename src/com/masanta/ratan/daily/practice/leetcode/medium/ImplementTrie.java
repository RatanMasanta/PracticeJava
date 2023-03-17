package com.masanta.ratan.daily.practice.leetcode.medium;

public class ImplementTrie {
    private TrieNode root;
    public ImplementTrie() {
        root = new TrieNode();
        root.val = ' ';
    }

    public void insert(String word) {
        TrieNode ws = root;
        for(int i = 0; i < word.length(); i++){
            char c = word.charAt(i);
            if(ws.children[c - 'a'] == null){
                ws.children[c - 'a'] = new TrieNode(c);
            }
            ws = ws.children[c - 'a'];
        }
        ws.isWord = true;
    }

    public boolean search(String word) {
        TrieNode ws = root;
        for(int i = 0; i < word.length(); i++){
            char c = word.charAt(i);
            if(ws.children[c - 'a'] == null) return false;
            ws = ws.children[c - 'a'];
        }
        return ws.isWord;
    }

    public boolean startsWith(String prefix) {
        TrieNode ws = root;
        for(int i = 0; i < prefix.length(); i++){
            char c = prefix.charAt(i);
            if(ws.children[c - 'a'] == null) return false;
            ws = ws.children[c - 'a'];
        }
        return true;
    }

    public static void main(String[] args){
        ImplementTrie implementTrie = new ImplementTrie();
        implementTrie.insert("qwerty");
        boolean searchWord = implementTrie.search("qwe");
        boolean searchWord2 = implementTrie.search("qwerty");
        boolean checkStartsWith = implementTrie.startsWith("q");
        System.out.println("The results are: \n" + "1. Searchword: "
                + searchWord + "\n2. Searchword2: " + searchWord2 +
                "\n3. StartsWith: " + checkStartsWith);
    }

     /*

    Revision URL: https://www.youtube.com/watch?v=dBGUmUQhjaM

     */
}


class TrieNode {

    public char val;
    public boolean isWord;
    public TrieNode[] children = new TrieNode[26];
    public TrieNode() {}
    TrieNode(char c){
        TrieNode node = new TrieNode();
        node.val = c;
    }
}

