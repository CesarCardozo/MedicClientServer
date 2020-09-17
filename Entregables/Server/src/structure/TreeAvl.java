package structure;

import java.util.ArrayList;

public class TreeAvl<T> {

	NodeAvl<T> root;
	int size = 0;

	public TreeAvl() {
		root = null;
	}

	public NodeAvl<T> rootTree() {
		return root;
	}

	// insertar nodo en arbolAVL, donde caste el nuevo objeto a el objeto
	// implementado el comparador.

	public void insert(T valor) throws Exception {
		Comparador dato;
		Logical h = new Logical(false);
		dato = (Comparador) valor;
		root = insertAvl(root, dato, h);
		size++;

	}

	private NodeAvl<T> insertAvl(NodeAvl<T> root, Comparador data, Logical h) throws Exception {
		NodeAvl<T> n1;

		if (root == null) {
			root = new NodeAvl(data);
			h.setLogical(true);
		} else if (data.lessThan(root.valueNode())) {
			NodeAvl<T> iz;
			iz = insertAvl((NodeAvl<T>) root.subTreeLeft(), data, h);
			root.branchLeft(iz);// ***************************************************
			// regresa por los nodos del camino de busqueda
			if (h.booleanValue()) {
				// decrementa el fe por aumento de la rama izquierda
				switch (root.fe) {
				case 1:
					root.fe = 0;
					h.setLogical(false);
					break;
				case 0:
					root.fe = -1;
					break;
				case -1: // aplicar rotacion a la izquierda
					n1 = (NodeAvl<T>) root.subTreeLeft();
					if (n1.fe == -1)
						root = rotationII(root, n1);
					else
						root = rotationRL(root, n1);
					h.setLogical(false);
				}
			}
		} else if (data.greaterThan(root.valueNode())) {
			NodeAvl<T> dr;
			dr = insertAvl((NodeAvl<T>) root.subTreeRigth(), data, h);
			root.branchRigth(dr);// **********************************************************
			// regresa por los nodos del camino de busqueda
			if (h.booleanValue()) {
				// decrementa el fe por aumento de la rama izquierda
				switch (root.fe) {
				case 1: // aplicar rotacion a la derecha
					n1 = (NodeAvl<T>) root.subTreeRigth();
					if (n1.fe == +1)
						root = rotationRR(root, n1);
					else
						root = rotationLR(root, n1);
					h.setLogical(false);
					break;
				case 0:
					root.fe = +1;
					break;
				case -1:
					root.fe = 0;
					h.setLogical(false);
				}
			}
		} else
			throw new Exception("No puede haber llave valor repetidas");
		return root;
	}

	// rotaciones en el arbolAVL

	private NodeAvl<T> rotationII(NodeAvl<T> n, NodeAvl<T> n1) {
		n.branchLeft(n1.subTreeRigth());
		n1.branchRigth(n);
		// actualización de los factores de equilibrio
		if (n1.fe == -1) // se cumple en la inserción
		{
			n.fe = 0;
			n1.fe = 0;
		} else {
			n.fe = -1;
			n1.fe = 1;
		}
		return n1;
	}

	private NodeAvl<T> rotationRR(NodeAvl<T> n, NodeAvl<T> n1) {
		n.branchRigth(n1.subTreeLeft());
		n1.branchLeft(n);
		// actualización de los factores de equilibrio
		if (n1.fe == +1) // se cumple en la inserción
		{
			n.fe = 0;
			n1.fe = 0;
		} else {
			n.fe = +1;
			n1.fe = -1;
		}
		return n1;
	}

	private NodeAvl<T> rotationRL(NodeAvl<T> n, NodeAvl<T> n1) {
		NodeAvl<T> n2;

		n2 = (NodeAvl<T>) n1.subTreeRigth();
		n.branchLeft(n2.subTreeRigth());
		n2.branchRigth(n);
		n1.branchRigth(n2.subTreeLeft());
		n2.branchLeft(n1);
		// actualización de los factores de equilibrio
		if (n2.fe == +1)
			n1.fe = -1;
		else
			n1.fe = 0;
		if (n2.fe == -1)
			n.fe = 1;
		else
			n.fe = 0;
		n2.fe = 0;
		return n2;
	}

	private NodeAvl<T> rotationLR(NodeAvl<T> n, NodeAvl<T> n1) {
		NodeAvl<T> n2;

		n2 = (NodeAvl<T>) n1.subTreeLeft();
		n.branchRigth(n2.subTreeLeft());
		n2.branchLeft(n);
		n1.branchLeft(n2.subTreeRigth());
		n2.branchRigth(n1);
		// actualización de los factores de equilibrio
		if (n2.fe == +1)
			n.fe = -1;
		else
			n.fe = 0;
		if (n2.fe == -1)
			n1.fe = 1;
		else
			n1.fe = 0;
		n2.fe = 0;
		return n2;
	}

//Borrado de un nodo en árbol AVL

	public void Delete(Object valor) throws Exception {
		Comparador dato;
		dato = (Comparador) valor;
		Logical flag = new Logical(false);
		root = deleteAvl(root, dato, flag);
		size--;
	}

	private NodeAvl<T> deleteAvl(NodeAvl<T> r, Comparador key, Logical changeHeight) throws Exception {
		if (r == null) {
			throw new Exception(" Nodo no encontrado ");
		} else if (key.lessThan(r.valueNode())) {
			NodeAvl<T> iz;
			iz = deleteAvl((NodeAvl<T>) r.subTreeLeft(), key, changeHeight);
			r.branchLeft(iz);
			if (changeHeight.booleanValue())
				r = balance1(r, changeHeight);
		} else if (key.greaterThan(r.valueNode())) {
			NodeAvl<T> dr;
			dr = deleteAvl((NodeAvl<T>) r.subTreeRigth(), key, changeHeight);
			r.branchRigth(dr);
			if (changeHeight.booleanValue())
				r = balance2(r, changeHeight);
		} else // Nodo encontrado
		{
			NodeAvl<T> q;
			q = r; // nodo a quitar del árbol
			if (q.subTreeLeft() == null) {
				r = (NodeAvl<T>) q.subTreeRigth();
				changeHeight.setLogical(true);
			} else if (q.subTreeRigth() == null) {
				r = (NodeAvl<T>) q.subTreeLeft();
				changeHeight.setLogical(true);
			} else { // tiene rama izquierda y derecha
				NodeAvl<T> iz;
				iz = replace(r, (NodeAvl<T>) r.subTreeLeft(), changeHeight);
				r.branchLeft(iz);
				if (changeHeight.booleanValue())
					r = balance1(r, changeHeight);
			}
			q = null;
		}
		return r;
	}

	private NodeAvl<T> replace(NodeAvl<T> n, NodeAvl<T> act, Logical cambiaAltura) {
		if (act.subTreeRigth() != null) {
			NodeAvl<T> d;
			d = replace(n, (NodeAvl<T>) act.subTreeRigth(), cambiaAltura);
			act.branchRigth(d);
			if (cambiaAltura.booleanValue())
				act = balance2(act, cambiaAltura);
		} else {
			n.setValue(act.valueNode());
			n = act;
			act = (NodeAvl<T>) act.subTreeLeft();
			n = null;
			cambiaAltura.setLogical(true);
		}
		return act;
	}

	private NodeAvl<T> balance1(NodeAvl<T> n, Logical cambiaAltura) {
		NodeAvl<T> n1;
		switch (n.fe) {
		case -1:
			n.fe = 0;
			break;
		case 0:
			n.fe = 1;
			cambiaAltura.setLogical(false);
			break;
		case +1: // se aplicar un tipo de rotación derecha
			n1 = (NodeAvl<T>) n.subTreeRigth();
			if (n1.fe >= 0) {
				if (n1.fe == 0) // la altura no vuelve a disminuir
					cambiaAltura.setLogical(false);
				n = rotationRR(n, n1);
			} else
				n = rotationLR(n, n1);
			break;
		}
		return n;
	}

	private NodeAvl<T> balance2(NodeAvl<T> n, Logical cambiaAltura) {
		NodeAvl<T> n1;

		switch (n.fe) {
		case -1: // Se aplica un tipo de rotación izquierda
			n1 = (NodeAvl<T>) n.subTreeLeft();
			if (n1.fe <= 0) {
				if (n1.fe == 0)
					cambiaAltura.setLogical(false);
				n = rotationII(n, n1);
			} else
				n = rotationRL(n, n1);
			break;
		case 0:
			n.fe = -1;
			cambiaAltura.setLogical(false);
			break;
		case +1:
			n.fe = 0;
			break;
		}

		return n;
	}

	public ArrayList<T> inOrden() {
		ArrayList<T> inOrden = new ArrayList<>();
		this.inOrden(this.root, inOrden);
		return inOrden;
	}

	private void inOrden(NodeAvl<T> node, ArrayList<T> list) {
		if (node != null) {
			inOrden(node.subTreeLeft(), list);
			list.add(node.valueNode());
			inOrden(node.subTreeRigth(), list);
		}
	}

	public void preOrden(NodeAvl<T> node, ArrayList<T> list) {
		if (node != null) {
			list.add(node.valueNode());
			preOrden(node.subTreeLeft(), list);
			preOrden(node.subTreeRigth(), list);
		}
	}

	public NodeAvl<T> search(T info) throws Exception {
		return search(info, this.root);
	}

	private NodeAvl<T> search(T info, NodeAvl<T> nodo) throws Exception {
		Comparador dato = (Comparador) info;
		if (nodo == null) {
			throw new Exception("No se encuentra el valor especificado");
		}
		if (dato.equalsTo(nodo.valueNode())) {
			return nodo;
		}
		if (dato.lessThan(nodo.valueNode())) {
			return search(info, nodo.subTreeLeft());
		}
		if (dato.greaterThan(nodo.valueNode())) {
			return search(info, nodo.subTreeRigth());
		}
		return null;
	}

	public int getSize() {
		return size;
	}

}