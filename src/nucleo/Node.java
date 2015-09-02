package nucleo;

import visual.NoVisual;

/**
 *
 * @author Matheus Alves
 */
public class Node {

  private final int value;
  public Node parent;
  private Node left;
  private Node right;
  private final NoVisual view;

  public Node(int value) {
    this.value = value;
    this.left = null;
    this.right = null;
    this.parent = null;
    this.view = new NoVisual(value);
  }

  public int getInfo() {
    return this.value;
  }

  public NoVisual getView() {
    return view;
  }

  public int deep() {
    if (isRoot()) {
      return 0;
    }
    return parent.deep() + 1;
  }

  public int height() {
    int leftValue = 0, rightValue = 0;

    if (hasLeft()) {
      leftValue = left.height() + 1;
    }
    if (hasRight()) {
      rightValue = right.height() + 1;
    }

    return leftValue > rightValue ? leftValue : rightValue;
  }

  public boolean isRoot() {
    return this.parent == null;
  }

  public boolean isLeaf() {
    return left == null && right == null;
  }

  public boolean hasLeft() {
    return left != null;
  }

  public boolean hasRight() {
    return right != null;
  }

  public Node nextRight() {
    return right;
  }

  public void setRight(Node node) {
    this.right = node;
    this.right.parent = this;
  }

  public Node nextLeft() {
    return left;
  }

  public void setLeft(Node node) {
    this.left = node;
    this.left.parent = this;

  }

}
