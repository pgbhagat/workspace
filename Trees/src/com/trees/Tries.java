package com.trees;
import java.util.HashMap;
import java.util.Map;

public class Tries {

	static class TrieNode {
		Map<Character, TrieNode> children;
		boolean isEndOfWord;

		TrieNode() {
			children = new HashMap<Character, Tries.TrieNode>();
			isEndOfWord = false;
		}

		private static final TrieNode root = new TrieNode();

		public void insertWord(String word) {
			if (word == null)
				return;

			TrieNode current = root;
			for (Character ch : word.toCharArray()) {
				TrieNode node = current.children.get(ch);
				if (node == null) {
					node = new TrieNode();
					current.children.put(ch, node);
				}
				current = node;
			}
			current.isEndOfWord = true;
		}

		public boolean searchWord(String word) {
			if (word == null)
				return false;
			TrieNode current = root;
			for (Character ch : word.toCharArray()) {
				TrieNode node = current.children.get(ch);
				if (node == null) {
					return false;
				}
				current = node;
			}
			return current.isEndOfWord;
		}

		public boolean deleteWord(String word) {

			if (word == null)
				return false;
			return delete(root, word, 0);

		}

		private boolean delete(TrieNode current, String word, int index) {

			if (index == word.length()) {
				if (!current.isEndOfWord)
					return false;
				current.isEndOfWord = false;
				return current.children.isEmpty();
			}
			if (current == null) {
				return false;
			}

			TrieNode node = current.children.get(word.charAt(index));
			boolean shouldDeleteNode = delete(node, word, index + 1);
			if (shouldDeleteNode) {
				current.children.remove(word.charAt(index));
				return current.children.isEmpty();
			}
			return shouldDeleteNode;
		}
	}

	public static void main(String[] args) {
		TrieNode trie = new Tries.TrieNode();
		trie.insertWord("hello");
		trie.insertWord("This");
		trie.insertWord("is");
		trie.insertWord("data");
		trie.insertWord("structure");

		System.out.println("hello isPresent - " + trie.searchWord("hello"));
		System.out.println("data isPresent - " + trie.searchWord("data"));
		System.out.println("duckTales isPresent - " + trie.searchWord("duckTales"));
		trie.deleteWord("hello");
		System.out.println("hello isPresent - " + trie.searchWord("hello"));

	}

}
