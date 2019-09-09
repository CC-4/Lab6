/*
MIT License

Copyright (c) 2016-2019 Andrés Castellanos

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/
package viper.tree;


/** General representation of a Viper AST node. */
public abstract class Node {

  /** node line number */
  protected final int line;

  /** node column */
  protected final int col;

  /**
   * Creates a new node.
   *
   * @param line node line number
   * @param col node column number
   */
  public Node(int line, int col) {
    this.line = line;
    this.col = col;
  }

  /**
   * Creates a String of a determined number of spaces.
   *
   * @param n number of spaces
   * @return a String of spaces
   */
  public static String pad(int n) {
    String s = "";
    for(int i=0; i < n; i++) {
      s += " ";
    }
    return s;
  }

}
