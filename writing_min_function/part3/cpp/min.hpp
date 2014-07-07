#include "concepts.hpp"

//Note 1: this code is Concept-enabled C++.
//Note 2: Still has errors!
template <TotallyOrdered T>
T const& min(T const& a, T const& b) {
	if (a < b) return a;
	return b;
}

//Wrong min version using predicates but the semantics is not specified.
template <typename T, typename Comparator>
T const& min_wrong(T const& a, T const& b, Comparator cmp) {
	if (cmp(a, b)) return a;
	return b;
}

template <typename T, StrictWeakOrdering Cmp>
	requires( Signature<Cmp, bool(T,T)>() )
T const& min(T const& a, T const& b, Cmp cmp) {
	if (cmp(a, b)) return a;
	return b;
}
