︠a8d3c169-4e0c-47e5-a5b0-1c03842d3d62︠
# +/- feladat: Írj függvényt masodik néven ami két paramétert vár és kiírja a másodikat.
def masodik(x,y):
    print(y)

masodik(2,"asd")
︡e2ff8635-855d-4376-90a7-9277e491848b︡{"stdout":"asd\n"}︡{"done":true}
︠034538db-2665-43bc-8ef7-520f8ae0671e︠
#gráfok
#sokféle konstruktorral létrehozható pl. csúcsok listája, élek listája
v = [1,2,3,4,5]
e = [[1,2],[2,3],[4,5],[5,2],[1,2]]
G = Graph([v,e])
G.show() #gráf kirajzolása
︡54b56003-9035-41e8-9669-e5b6b11c7538︡{"file":{"filename":"/home/user/.sage/temp/project-57800ab4-f667-4ced-a1ef-d775d4af8cb1/633/tmp_j3d2o23g.svg","show":true,"text":null,"uuid":"8f32781b-751e-424d-a45c-56f6601bd42b"},"once":false}︡{"done":true}
︠2a033851-caaf-4135-945e-fce229075401︠
# szótár konstruktor (adott csúcsbol mely másik csúcsokba megy él)
d = {1: [0,1,2,3], 2:[5], 3:[1,3,5], 'b':[]}
G = Graph(d)
G.show()
︡78b15dd3-1564-4489-93b0-e679e8dfb0b2︡{"file":{"filename":"/home/user/.sage/temp/project-57800ab4-f667-4ced-a1ef-d775d4af8cb1/633/tmp_54o1bm25.svg","show":true,"text":null,"uuid":"5f8e2b7a-60f3-480e-8e15-9bd2bbfca89a"},"once":false}︡{"done":true}
︠beeb7dd0-c1ad-4e39-903f-9af761f689d9︠
Graph?
︡0b78247f-768d-4eac-bfbe-08afa7add1fa︡{"code":{"filename":null,"lineno":-1,"mode":"text/x-rst","source":"File: /ext/sage/9.6/local/var/lib/sage/venv-python3.10.3/lib/python3.10/site-packages/sage/graphs/graph.py\nDocstring :\nUndirected graph.\n\nA graph is a set of vertices connected by edges. See the\nhttps://en.wikipedia.org/wiki/Graph_(mathematics) for more\ninformation. For a collection of pre-defined graphs, see the\n\"graph_generators\" module.\n\nA \"Graph\" object has many methods whose list can be obtained by\ntyping \"g.<tab>\" (i.e. hit the 'tab' key) or by reading the\ndocumentation of \"graph\", \"generic_graph\", and \"digraph\".\n\nINPUT:\n\nBy default, a \"Graph\" object is simple (i.e. no *loops* nor\n*multiple edges*) and unweighted. This can be easily tuned with the\nappropriate flags (see below).\n\n* \"data\" -- can be any of the following (see the \"format\"\n  argument):\n\n  1. \"Graph()\" -- build a graph on 0 vertices.\n\n  2. \"Graph(5)\" -- return an edgeless graph on the 5 vertices\n     0,...,4.\n\n  3. \"Graph([list_of_vertices, list_of_edges])\" -- returns a graph\n     with given vertices/edges.\n\n     To bypass auto-detection, prefer the more explicit \"Graph([V,\n     E], format='vertices_and_edges')\".\n\n  4. \"Graph(list_of_edges)\" -- return a graph with a given list of\n     edges (see documentation of \"add_edges()\").\n\n     To bypass auto-detection, prefer the more explicit \"Graph(L,\n     format='list_of_edges')\".\n\n  5. \"Graph({1: [2, 3, 4], 3: [4]})\" -- return a graph by\n     associating to each vertex the list of its neighbors.\n\n     To bypass auto-detection, prefer the more explicit \"Graph(D,\n     format='dict_of_lists')\".\n\n  6. \"Graph({1: {2: 'a', 3:'b'} ,3:{2:'c'}})\" -- return a graph by\n     associating a list of neighbors to each vertex and providing\n     its edge label.\n\n     To bypass auto-detection, prefer the more explicit \"Graph(D,\n     format='dict_of_dicts')\".\n\n     For graphs with multiple edges, you can provide a list of\n     labels instead, e.g.: \"Graph({1: {2: ['a1', 'a2'], 3:['b']}\n     ,3:{2:['c']}})\".\n\n  7. \"Graph(a_symmetric_matrix)\" -- return a graph with given\n     (weighted) adjacency matrix (see documentation of\n     \"adjacency_matrix()\").\n\n     To bypass auto-detection, prefer the more explicit \"Graph(M,\n     format='adjacency_matrix')\". To take weights into account, use\n     \"format='weighted_adjacency_matrix'\" instead.\n\n  8. \"Graph(a_nonsymmetric_matrix)\" -- return a graph with given\n     incidence matrix (see documentation of \"incidence_matrix()\").\n\n     To bypass auto-detection, prefer the more explicit \"Graph(M,\n     format='incidence_matrix')\".\n\n  9. \"Graph([V, f])\" -- return a graph from a vertex set \"V\" and a\n     *symmetric* function \"f\". The graph contains an edge u,v\n     whenever \"f(u,v)\" is \"True\".. Example: \"Graph([ [1..10],\n     lambda x,y: abs(x-y).is_square()])\"\n\n  10. \"Graph(':I`ES@obGkqegW~')\" -- return a graph from a graph6 or\n      sparse6 string (see documentation of \"graph6_string()\" or\n      \"sparse6_string()\").\n\n  11. \"Graph(a_seidel_matrix, format='seidel_adjacency_matrix')\" --\n      return a graph with a given Seidel adjacency matrix (see\n      documentation of \"seidel_adjacency_matrix()\").\n\n  12. \"Graph(another_graph)\" -- return a graph from a Sage\n      (di)graph, pygraphviz graph, NetworkX graph, or igraph graph.\n\n* \"pos\" -- a positioning dictionary (cf. documentation of\n  \"layout()\"). For example, to draw 4 vertices on a square:\n\n     {0: [-1,-1],\n      1: [ 1,-1],\n      2: [ 1, 1],\n      3: [-1, 1]}\n\n* \"name\" -- (must be an explicitly named parameter, i.e.,\n     \"name=\"complete\")\" gives the graph a name\n\n* \"loops\" -- boolean (default: \"None\"); whether to allow loops\n  (ignored\n     if data is an instance of the \"Graph\" class)\n\n* \"multiedges\" -- boolean (default: \"None\"); whether to allow\n  multiple\n     edges (ignored if data is an instance of the \"Graph\" class).\n\n* \"weighted\" -- boolean (default: \"None\"); whether graph thinks of\n  itself as weighted or not. See \"weighted()\".\n\n* \"format\" -- if set to \"None\" (default), \"Graph\" tries to guess\n  input's format. To avoid this possibly time-consuming step, one\n  of the following values can be specified (see description above):\n  \"\"int\"\", \"\"graph6\"\", \"\"sparse6\"\", \"\"rule\"\", \"\"list_of_edges\"\",\n  \"\"dict_of_lists\"\", \"\"dict_of_dicts\"\", \"\"adjacency_matrix\"\",\n  \"\"weighted_adjacency_matrix\"\", \"\"seidel_adjacency_matrix\"\",\n  \"\"incidence_matrix\"\", \"\"NX\"\", \"\"igraph\"\".\n\n* \"sparse\" -- boolean (default: \"True\"); \"sparse=True\" is an alias\n  for \"data_structure=\"sparse\"\", and \"sparse=False\" is an alias for\n  \"data_structure=\"dense\"\".\n\n* \"data_structure\" -- one of the following (for more information,\n  see \"overview\")\n\n     * \"\"dense\"\" -- selects the \"dense_graph\" backend.\n\n     * \"\"sparse\"\" -- selects the \"sparse_graph\" backend.\n\n     * \"\"static_sparse\"\" -- selects the \"static_sparse_backend\"\n       (this backend is faster than the sparse backend and smaller\n       in memory, and it is immutable, so that the resulting graphs\n       can be used as dictionary keys).\n\n* \"immutable\" -- boolean (default: \"False\"); whether to create a\n  immutable graph. Note that \"immutable=True\" is actually a\n  shortcut for \"data_structure='static_sparse'\". Set to \"False\" by\n  default.\n\n* \"vertex_labels\" -- boolean (default: \"True\"); whether to allow\n  any object as a vertex (slower), or only the integers 0,...,n-1,\n  where n is the number of vertices.\n\n* \"convert_empty_dict_labels_to_None\" -- this arguments sets the\n  default\n     edge labels used by NetworkX (empty dictionaries) to be\n     replaced by \"None\", the default Sage edge label. It is set to\n     \"True\" iff a NetworkX graph is on the input.\n\nEXAMPLES:\n\nWe illustrate the first seven input formats (the other two involve\npackages that are currently not standard in Sage):\n\n1. An integer giving the number of vertices:\n\n      sage: g = Graph(5); g\n      Graph on 5 vertices\n      sage: g.vertices()\n      [0, 1, 2, 3, 4]\n      sage: g.edges()\n      []\n\n2. A dictionary of dictionaries:\n\n      sage: g = Graph({0:{1:'x',2:'z',3:'a'}, 2:{5:'out'}}); g\n      Graph on 5 vertices\n\n   The labels ('x', 'z', 'a', 'out') are labels for edges. For\n   example, 'out' is the label for the edge on 2 and 5. Labels can\n   be used as weights, if all the labels share some common parent.:\n\n      sage: a, b, c, d, e, f = sorted(SymmetricGroup(3))              # optional - sage.groups\n      sage: Graph({b: {d: 'c', e: 'p'}, c: {d: 'p', e: 'c'}})         # optional - sage.groups\n      Graph on 4 vertices\n\n3. A dictionary of lists:\n\n      sage: g = Graph({0:[1,2,3], 2:[4]}); g\n      Graph on 5 vertices\n\n4. A list of vertices and a function describing adjacencies. Note\n   that the list of vertices and the function must be enclosed in a\n   list (i.e., [list of vertices, function]).\n\n   Construct the Paley graph over GF(13).:\n\n      sage: g=Graph([GF(13), lambda i,j: i!=j and (i-j).is_square()])\n      sage: g.vertices()\n      [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12]\n      sage: g.adjacency_matrix()\n      [0 1 0 1 1 0 0 0 0 1 1 0 1]\n      [1 0 1 0 1 1 0 0 0 0 1 1 0]\n      [0 1 0 1 0 1 1 0 0 0 0 1 1]\n      [1 0 1 0 1 0 1 1 0 0 0 0 1]\n      [1 1 0 1 0 1 0 1 1 0 0 0 0]\n      [0 1 1 0 1 0 1 0 1 1 0 0 0]\n      [0 0 1 1 0 1 0 1 0 1 1 0 0]\n      [0 0 0 1 1 0 1 0 1 0 1 1 0]\n      [0 0 0 0 1 1 0 1 0 1 0 1 1]\n      [1 0 0 0 0 1 1 0 1 0 1 0 1]\n      [1 1 0 0 0 0 1 1 0 1 0 1 0]\n      [0 1 1 0 0 0 0 1 1 0 1 0 1]\n      [1 0 1 1 0 0 0 0 1 1 0 1 0]\n\n   Construct the line graph of a complete graph.:\n\n      sage: g=graphs.CompleteGraph(4)\n      sage: line_graph=Graph([g.edges(labels=false), \\\n             lambda i,j: len(set(i).intersection(set(j)))>0], \\\n             loops=False)\n      sage: line_graph.vertices()\n      [(0, 1), (0, 2), (0, 3), (1, 2), (1, 3), (2, 3)]\n      sage: line_graph.adjacency_matrix()\n      [0 1 1 1 1 0]\n      [1 0 1 1 0 1]\n      [1 1 0 0 1 1]\n      [1 1 0 0 1 1]\n      [1 0 1 1 0 1]\n      [0 1 1 1 1 0]\n\n5. A graph6 or sparse6 string: Sage automatically recognizes\n   whether a string is in graph6 or sparse6 format:\n\n      sage: s = ':I`AKGsaOs`cI]Gb~'\n      sage: Graph(s,sparse=True)\n      Looped multi-graph on 10 vertices\n\n      sage: G = Graph('G?????')\n      sage: G = Graph(\"G'?G?C\")\n      Traceback (most recent call last):\n      ...\n      RuntimeError: the string seems corrupt: valid characters are\n      ?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[^_`abcdefghijklmnopqrstuvwxyz{|}~\n      sage: G = Graph('G??????')\n      Traceback (most recent call last):\n      ...\n      RuntimeError: the string (G??????) seems corrupt: for n = 8, the string is too long\n\n      sage: G = Graph(\":I'AKGsaOs`cI]Gb~\")\n      Traceback (most recent call last):\n      ...\n      RuntimeError: the string seems corrupt: valid characters are\n      ?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[^_`abcdefghijklmnopqrstuvwxyz{|}~\n\n   There are also list functions to take care of lists of graphs:\n\n      sage: s = ':IgMoqoCUOqeb\\n:I`AKGsaOs`cI]Gb~\\n:I`EDOAEQ?PccSsge\\\\N\\n'\n      sage: graphs_list.from_sparse6(s)\n      [Looped multi-graph on 10 vertices, Looped multi-graph on 10 vertices, Looped multi-graph on 10 vertices]\n\n6. A Sage matrix: Note: If format is not specified, then Sage\n   assumes a symmetric square matrix is an adjacency matrix,\n   otherwise an incidence matrix.\n\n   * an adjacency matrix:\n\n        sage: M = graphs.PetersenGraph().am(); M\n        [0 1 0 0 1 1 0 0 0 0]\n        [1 0 1 0 0 0 1 0 0 0]\n        [0 1 0 1 0 0 0 1 0 0]\n        [0 0 1 0 1 0 0 0 1 0]\n        [1 0 0 1 0 0 0 0 0 1]\n        [1 0 0 0 0 0 0 1 1 0]\n        [0 1 0 0 0 0 0 0 1 1]\n        [0 0 1 0 0 1 0 0 0 1]\n        [0 0 0 1 0 1 1 0 0 0]\n        [0 0 0 0 1 0 1 1 0 0]\n        sage: Graph(M)\n        Graph on 10 vertices\n\n        sage: Graph(matrix([[1,2],[2,4]]),loops=True,sparse=True)\n        Looped multi-graph on 2 vertices\n\n        sage: M = Matrix([[0,1,-1],[1,0,-1/2],[-1,-1/2,0]]); M\n        [   0    1   -1]\n        [   1    0 -1/2]\n        [  -1 -1/2    0]\n        sage: G = Graph(M,sparse=True); G\n        Graph on 3 vertices\n        sage: G.weighted()\n        True\n\n   * an incidence matrix:\n\n        sage: M = Matrix(6, [-1,0,0,0,1, 1,-1,0,0,0, 0,1,-1,0,0, 0,0,1,-1,0, 0,0,0,1,-1, 0,0,0,0,0]); M\n        [-1  0  0  0  1]\n        [ 1 -1  0  0  0]\n        [ 0  1 -1  0  0]\n        [ 0  0  1 -1  0]\n        [ 0  0  0  1 -1]\n        [ 0  0  0  0  0]\n        sage: Graph(M)\n        Graph on 6 vertices\n\n        sage: Graph(Matrix([[1],[1],[1]]))\n        Traceback (most recent call last):\n        ...\n        ValueError: there must be one or two nonzero entries per column in an incidence matrix, got entries [1, 1, 1] in column 0\n        sage: Graph(Matrix([[1],[1],[0]]))\n        Graph on 3 vertices\n\n        sage: M = Matrix([[0,1,-1],[1,0,-1],[-1,-1,0]]); M\n        [ 0  1 -1]\n        [ 1  0 -1]\n        [-1 -1  0]\n        sage: Graph(M,sparse=True)\n        Graph on 3 vertices\n\n        sage: M = Matrix([[0,1,1],[1,0,1],[-1,-1,0]]); M\n        [ 0  1  1]\n        [ 1  0  1]\n        [-1 -1  0]\n        sage: Graph(M)\n        Traceback (most recent call last):\n        ...\n        ValueError: there must be one or two nonzero entries per column in an incidence matrix, got entries [1, 1] in column 2\n\n      Check that https://trac.sagemath.org/9714 is fixed:\n\n         sage: MA = Matrix([[1,2,0], [0,2,0], [0,0,1]])\n         sage: GA = Graph(MA, format='adjacency_matrix')\n         sage: MI = GA.incidence_matrix(oriented=False)\n         sage: MI\n         [2 1 1 0 0 0]\n         [0 1 1 2 2 0]\n         [0 0 0 0 0 2]\n         sage: Graph(MI).edges(labels=None)\n         [(0, 0), (0, 1), (0, 1), (1, 1), (1, 1), (2, 2)]\n\n         sage: M = Matrix([[1], [-1]]); M\n         [ 1]\n         [-1]\n         sage: Graph(M).edges()\n         [(0, 1, None)]\n\n7. A Seidel adjacency matrix:\n\n      sage: from sage.combinat.matrices.hadamard_matrix import \\\n      ....:  regular_symmetric_hadamard_matrix_with_constant_diagonal as rshcd\n      sage: m=rshcd(16,1)- matrix.identity(16)\n      sage: Graph(m,format=\"seidel_adjacency_matrix\").is_strongly_regular(parameters=True)\n      (16, 6, 2, 2)\n\n8. List of edges, or labelled edges:\n\n      sage: g = Graph([(1,3),(3,8),(5,2)])\n      sage: g\n      Graph on 5 vertices\n\n      sage: g = Graph([(1,2,\"Peace\"),(7,-9,\"and\"),(77,2, \"Love\")])\n      sage: g\n      Graph on 5 vertices\n      sage: g = Graph([(0, 2, '0'), (0, 2, '1'), (3, 3, '2')], loops=True, multiedges=True)\n      sage: g.loops()\n      [(3, 3, '2')]\n\n9. A NetworkX MultiGraph:\n\n      sage: import networkx\n      sage: g = networkx.MultiGraph({0:[1,2,3], 2:[4]})\n      sage: Graph(g)\n      Multi-graph on 5 vertices\n\n10. A NetworkX graph:\n\n       sage: import networkx\n       sage: g = networkx.Graph({0:[1,2,3], 2:[4]})\n       sage: DiGraph(g)\n       Digraph on 5 vertices\n\n11. An igraph Graph (see also \"igraph_graph()\"):\n\n       sage: import igraph                      # optional - python_igraph\n       sage: g = igraph.Graph([(0, 1), (0, 2)]) # optional - python_igraph\n       sage: Graph(g)                           # optional - python_igraph\n       Graph on 3 vertices\n\n    If \"vertex_labels\" is \"True\", the names of the vertices are\n    given by the vertex attribute \"'name'\", if available:\n\n       sage: g = igraph.Graph([(0,1),(0,2)], vertex_attrs={'name':['a','b','c']})  # optional - python_igraph\n       sage: Graph(g).vertices()                                                   # optional - python_igraph\n       ['a', 'b', 'c']\n       sage: g = igraph.Graph([(0,1),(0,2)], vertex_attrs={'label':['a','b','c']}) # optional - python_igraph\n       sage: Graph(g).vertices()                                                   # optional - python_igraph\n       [0, 1, 2]\n\n    If the igraph Graph has edge attributes, they are used as edge\n    labels:\n\n       sage: g = igraph.Graph([(0,1),(0,2)], edge_attrs={'name':['a','b'], 'weight':[1,3]}) # optional - python_igraph\n       sage: Graph(g).edges()                                                               # optional - python_igraph\n       [(0, 1, {'name': 'a', 'weight': 1}), (0, 2, {'name': 'b', 'weight': 3})]\n\nWhen defining an undirected graph from a function \"f\", it is *very*\nimportant that \"f\" be symmetric. If it is not, anything can happen:\n\n   sage: f_sym = lambda x,y: abs(x-y) == 1\n   sage: f_nonsym = lambda x,y: (x-y) == 1\n   sage: G_sym = Graph([[4,6,1,5,3,7,2,0], f_sym])\n   sage: G_sym.is_isomorphic(graphs.PathGraph(8))\n   True\n   sage: G_nonsym = Graph([[4,6,1,5,3,7,2,0], f_nonsym])\n   sage: G_nonsym.size()\n   4\n   sage: G_nonsym.is_isomorphic(G_sym)\n   False\n\nBy default, graphs are mutable and can thus not be used as a\ndictionary key:\n\n   sage: G = graphs.PetersenGraph()\n   sage: {G:1}[G]\n   Traceback (most recent call last):\n   ...\n   TypeError: This graph is mutable, and thus not hashable. Create an immutable copy by `g.copy(immutable=True)`\n\nWhen providing the optional arguments\n\"data_structure=\"static_sparse\"\" or \"immutable=True\" (both mean the\nsame), then an immutable graph results.\n\n   sage: G_imm = Graph(G, immutable=True)\n   sage: H_imm = Graph(G, data_structure='static_sparse')\n   sage: G_imm == H_imm == G\n   True\n   sage: {G_imm:1}[H_imm]\n   1"}}︡{"done":true}
︠a7c4c49a-cb62-45a3-9e3f-609e9499b13d︠
d = {1: [0,1,2,3], 2:[5], 3:[1,3,5], 'b':[]}
G = Graph(d)
G.allow_loops(new = False) #allow_loops állítja, hogy a gráf tartalmazhat-e hurokéleket new = True - igen; new = False - nem
# allow_multiple_ages() ugyan ez csak párhuzamos élekkel
G.show()
︡bdefbf4d-a27c-45b8-891f-d68fecf7c8bd︡{"file":{"filename":"/home/user/.sage/temp/project-57800ab4-f667-4ced-a1ef-d775d4af8cb1/633/tmp_e864ag9l.svg","show":true,"text":null,"uuid":"9b33bc4b-651c-43b3-83ca-6d2f25d5d9b4"},"once":false}︡{"done":true}
︠61314b26-90bb-4381-902c-e700c899f4c6︠
G.chromatic_number() #legkevesebb szín ami a gráf kiszinezéséhez szükséges, hogy ne legyen két szomszédos csúcs azonos színű, csak hurokmentes
︡d831af1d-5ccd-4be1-b7fe-beed9940c55c︡{"stdout":"2\n"}︡{"done":true}
︠d3328f04-8e47-48ef-b65e-40cb041e58b0︠
#feladat: készítsünk gráfot, ahol a csúcsok azok az [x,y] párok ahol x < y, azon v1,v2 csúcsok között van él, ahol v1.x + v1.y és v2.x + v2.y relatív prímek, azaz legnagyobb közös osztójuk 1 x,y eleme [1,5]
v = [Set([x,y]) for x in [1..5] for y in [x+1..5]]
v
︡be73216b-bbca-4030-b92a-be900af771c2︡{"stdout":"[{1, 2}, {1, 3}, {1, 4}, {1, 5}, {2, 3}, {2, 4}, {2, 5}, {3, 4}, {3, 5}, {4, 5}]\n"}︡{"done":true}
︠36a8c3f4-bd9c-4d5c-bd93-125893267924︠
def arePairSumsRelativePrimes(x,y):
    #gcd két szám legnagyobb közös osztóját adja vissza
    return gcd(sum(x),sum(y)) == 1

#Cúcshalmaz, illeszkedési függvény konstruktor
#G = Graph([v,arePairSumsRelativePrimes])
G = Graph([v,lambda x,y: gcd(sum(x),sum(y)) == 1]) # használhatunk lambdát is
G.show()
︡ccabc525-9569-4d49-8356-98abb7f75c1e︡{"file":{"filename":"/home/user/.sage/temp/project-57800ab4-f667-4ced-a1ef-d775d4af8cb1/633/tmp_b8loirln.svg","show":true,"text":null,"uuid":"832f9f70-2d8b-422e-a07e-f357a068c673"},"once":false}︡{"done":true}
︠2c05354e-2814-4376-827a-f7529fee894e︠
G.hamiltonian_cycle().show() #hamilton-kör megjelenítése
︡70543715-5fc0-4354-8be0-9fc6d2590490︡{"file":{"filename":"/home/user/.sage/temp/project-57800ab4-f667-4ced-a1ef-d775d4af8cb1/633/tmp_6znplg28.svg","show":true,"text":null,"uuid":"eb3d5024-88df-470f-9e5e-2dd4caf8cfd6"},"once":false}︡{"done":true}
︠979ac0b5-4552-4fb3-8ee0-a73142dc0d13︠
G.shortest_path(Set([1,2]),Set([2,4])) #legrövidebb út, valamiért a {}-as halmazjelölés nem működik
︡f1f4c664-acca-4733-b296-65d869f91b12︡{"stdout":"[{1, 2}, {1, 4}, {2, 4}]\n"}︡{"done":true}
︠2ce78ee4-1cd5-448d-892c-b8f5fcf43700︠
G = Graph([[1,2,3,4],[(2,2,'c'),(1,2,'a'),(1,3,'b'),(1,3,'d'),(3,4,'e'),(2,4,'f')]], loops=True, multiedges=True) # hurokélek, párhuzamos élek
G.show(edge_labels = True) # címkézett élek
︡e96b78ca-0b58-4aab-84cf-e105e04ada79︡{"file":{"filename":"/home/user/.sage/temp/project-57800ab4-f667-4ced-a1ef-d775d4af8cb1/633/tmp_bdrnusaf.svg","show":true,"text":null,"uuid":"3f3ebe8e-0784-4086-9a8a-0a99a8353874"},"once":false}︡{"done":true}
︠99c02faf-5dc4-486b-8d76-ea0e814ff659︠
G.all_paths(4,3) # összes út
︡f85789ba-d1c3-4a48-83c1-751e7f1dc5d7︡{"stdout":"[[4, 2, 1, 3], [4, 3]]\n"}︡{"done":true}
︠c8af27d4-45d6-4930-a44f-cd6392965a6d︠
G = DiGraph([[1,2,3,4,5],[(1,2),(3,1),(2,5)]]) #irányított gráf
G.show()
︡52b22632-4edd-4acc-9adf-9098a1953dd6︡{"file":{"filename":"/home/user/.sage/temp/project-57800ab4-f667-4ced-a1ef-d775d4af8cb1/633/tmp_270dgvqb.svg","show":true,"text":null,"uuid":"5784eead-33e8-4d18-9377-496bef0c8a7e"},"once":false}︡{"done":true}
︠17d6f05a-5a97-4513-8a70-52027bffcd9a︠
# feladat:
# isReflexive(halmaz,reláció) reláció reflexív-e az adott halmazra? pl. isReflexive({1,2,3},[[1,1],[2,2],[3,3]])
# isTransitive(halmaz,reláció)
# domain() értelmezési tartomány megadása
# image(rel,halmaz) reláció halmazra vonatkozó képének megadása
#Eq_classes adja meg egy ekvivalenciareláció esetén megadja ekvivalenciaosztályait

def domain(rel):
    return {i for (i,_) in rel}

def isReflexive(set,rel):
    for i in set:
        if [i,i] not in rel:
            return False
    return True

def isTransitive(set,rel):
    cprod = [(i,j,k) for i in set for j in set for k in set]
    for (i,j,k) in cprod:
        if [i,j] in rel and [j,k] in rel and not [i,k] in rel:
            return False
    return True

def image(rel,set):
    return {j for (i,j) in rel if i in set}

def Eq_classes(rel):
    dmn = domain(rel)
    return {Set(image(rel,{i})) for i in dmn}

domain([[1,1],[1,2],[1,3],[2,4],[2,5],[3,2]])
isReflexive({1,2,3},[[1,1],[1,2],[1,3],[2,2],[2,4],[2,5],[3,2],[3,3]])
isReflexive({1,2,3,4},[[1,1],[1,2],[1,3],[2,4],[2,5],[3,2]])
isTransitive({1,2,3},[[1,1],[1,2],[1,3],[2,2],[2,4],[2,5],[3,2],[3,3]])
isTransitive({1,2,3,4},[[1,1],[1,2],[1,3],[2,4],[2,5],[3,2]])
image([[1,1],[1,2],[1,3],[2,2],[2,4],[2,5],[3,2],[3,3]],{1,2,3})
Eq_classes([[1,1],[1,5],[2,2],[3,3],[3,4],[4,3],[4,4],[5,1],[5,5]])
︡cfb7b83e-383c-404b-8e9c-a942c4abddfe︡{"stdout":"{1, 2, 3}\n"}︡{"stdout":"True\n"}︡{"stdout":"False\n"}︡{"stdout":"True\n"}︡{"stdout":"False\n"}︡{"stdout":"{1, 2, 3, 4, 5}\n"}︡{"stdout":"{{3, 4}, {2}, {1, 5}}\n"}︡{"done":true}









