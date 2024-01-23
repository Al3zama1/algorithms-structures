package com.abranlezama.structures.unionfind;

public class UnionFind {

    // number of elements in this union find
    private final int size;
    // used to track the size of each of the components
    private final int[] componentSize;
    // tracks the number of components in the union find
    private int numComponents;

    // root[i] points to the parent of i, if root[i] = i, then i is a root node
    private final int[] root;

    public UnionFind(int size) {
        if (size <= 0) throw new IllegalArgumentException("Size <= 0 is not allowed");
        // initially, there is as many components as number of elements
        this.size = numComponents = size;
        componentSize = new int[size];
        root = new int[size];

        for (int i  = 0; i < size; i++) {
            root[i] = i; // link to itself initially, (self root)
            componentSize[i] = 1; // each component if originally of size 1
        }
    }

    // find which component/set 'p' belongs to, takes amortized constant time
    public int find(int p) {
        // find the root of the component/set
        int i = p;
        while (i != root[i]) i = root[i];

        /*
        compress the path leading back to the root.
        Doing this operation is called "path compression" and is what gives us
        amortized time complexity
         */
        while (p != i) {
            int next = root[p];
            root[p] = i;
            p = next;
        }

        return i;
    }

    // This is an alternative recursive formulation for the find method
    // public int find(int p) {
    //   if (p == id[p]) return p;
    //   return id[p] = find(id[p]);
    // }

    // return whether elements 'p' and 'q' are in the same component/set.
    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    // return the size of the components/set 'p' belongs to
    public int componentSize(int p) {
        return componentSize[find(p)];
    }

    // return the number of elements in this UnionFind/Disjoint set
    public int size() {
        return size;
    }

    // return the number of remaining components/sets
    public int components() {
        return numComponents;
    }

    // unify the components/sets containing elements 'p' and 'q'
    public void unify(int p, int q) {
        // these elements are already in the same group
        if (connected(p, q)) return;

        int root1 = find(p);
        int root2 = find(q);

        // merge smaller component/set into the larger one
        if (componentSize[root1] < componentSize[root2]) {
            componentSize[root2] += componentSize[root1];
            root[root1] = root2;
            componentSize[root1] = 0;
        } else {
            componentSize[root1] += componentSize[root2];
            root[root2] = root1;
            componentSize[root2] = 0;
        }

        // since the roots found are different we know that the number of
        // components/sets has decreased by one
        numComponents--;
    }
}
