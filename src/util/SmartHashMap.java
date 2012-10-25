package util;

import java.util.HashMap;

public class SmartHashMap<E, K> extends HashMap<E, K> {
	private static final long serialVersionUID = -983895123107205578L;
	private Factory<K> fac;

	public SmartHashMap(Factory<K> factory) {
		super();
		fac = factory;
	}

	@Override
	public K get(Object obj) {
		K res = super.get(obj);
		if (res == null) {
			K temp = fac.create();
			put((E) obj, temp);
			return temp;
		}
		return res;
	}
}