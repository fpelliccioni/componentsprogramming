#ifndef CONCEPTS_HPP_
#define CONCEPTS_HPP_

//C++ Concepts emulation for using with and old Non-ConceptsTS compiler

#define TotallyOrdered typename
#define StrictWeakOrdering typename
#define requires(...) 


#include "helper_code/function_traits.hpp"

template<typename T>
using BaseType = typename std::remove_cv<typename std::remove_reference<T>::type>::type;

template <typename F>
using ArgumentType = BaseType<typename function_traits<F>::template arg<0>::type>;

template <typename T, typename U>
constexpr bool SameType() {
    return std::is_same<T, U>::value;
}

#endif  // CONCEPTS_HPP_
