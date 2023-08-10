package com.abranlezama.structures.unionfind;

public class UnionFind {
    private final int size; // number of elements in the union find
    private final int[] componentSize; // track size of each component
    // points to the parent o i, if i[i] == i, then i is a root node
    private final int[] id;
    private int numComponents; // track number of components in the union find

    public UnionFind(int size) {
        if (size <= 0) throw new IllegalArgumentException("Size <= 0 is not allowed");

        this.size = numComponents = size;
        id = componentSize = new int[size];

        for (int i = 0; i < size; i++) {
            id[i] = i; // link to itself (self-root)
            componentSize[i] = 1; // each component is initially of size 1
        }
    }

    //find which component/set 'p' belongs to, take amortized constant time
    public int find(int p) {
        // find root of the component/set
        int root = p;
        while (root != id[root]) root = id[root];

        /*
        compress path leading back to the root. Doing so, is called 'path compression'
        and is what gives us amortized time complexity
         */

        while (p != root) {
            int next = id[p];
            id[p] = root;
            p = next;
        }
        return root;
    }

    // This is an alternative recursive formulation for the find method
    // public int find(int p) {
    //   if (p == id[p]) return p;
    //   return id[p] = find(id[p]);
    // }

    // determine if elements 'p' and 'q' are in the same component/set
    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    // determine size of the component/set that 'p' belongs to
    public int componentSize(int p) {
        return componentSize[find(p)];
    }

    public int size() {
        return size;
    }

    // return number of remaining components/sets
    public int components() {
        return numComponents;
    }

    // unify the components/sets containing elements 'p' and 'q'
    public void unify(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);

        // these elements are already in the same group
        if (pRoot == qRoot) return;

        // merge smaller component/set into the larger one
        if (componentSize[pRoot] < componentSize[qRoot]) {
            componentSize[qRoot] += componentSize[pRoot];
            id[pRoot] = qRoot;
        } else {
            componentSize[pRoot] += componentSize[qRoot];
            id[qRoot] = pRoot;
        }

        /*
        since the number of roots found are different, we know tha the number of
        components/sets has decreased by one after merging
         */
        numComponents--;
    }

}
