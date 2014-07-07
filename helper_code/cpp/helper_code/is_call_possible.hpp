// Template metaprogramming hacks
// Don't waste your time doing such things

template <typename Type>
class has_member
{
	class yes { char m;};
	class no { yes m[2];};

	struct BaseMixin
	{
	 void operator()(){}
	};

	struct Base : public Type, public BaseMixin {};

	template <typename T, T t>  class Helper{};

	template <typename U>
	static no deduce(U*, Helper<void (BaseMixin::*)(), &U::operator()>* = 0);
	static yes deduce(...);

public:
	static const bool result = sizeof(yes) == sizeof(deduce((Base*)(0)));
};

namespace details
{
	template <typename type>
	class void_exp_result
	{};

	template <typename type, typename U>
	U const& operator,(U const&, void_exp_result<type>);

	template <typename type, typename U>
	U& operator,(U&, void_exp_result<type>);

	template <typename src_type, typename dest_type>
	struct clone_constness
	{
		typedef dest_type type;
	};

	template <typename src_type, typename dest_type>
	struct clone_constness<const src_type, dest_type>
	{
		typedef const dest_type type;
	};
}

template <typename type, typename call_details>
struct is_call_possible
{
private:
	class yes {};
	class no { yes m[2]; };

	struct derived : public type
	{
		using type::operator();
		no operator()(...) const;
	};

	typedef typename details::clone_constness<type, derived>::type derived_type;

	template <typename T, typename due_type>
	struct return_value_check
	{
		static yes deduce(due_type);
		static no deduce(...);
		static no deduce(no);
		static no deduce(details::void_exp_result<type>);
	};

	template <typename T>
	struct return_value_check<T, void>
	{
		static yes deduce(...);
		static no deduce(no);
	};

	template <typename F, bool has>
	struct impl
	{
		static const bool value = false;
	};

	template <typename r, typename... args>
	struct impl<r(args...), true>
	{
		static const bool value = sizeof(
						return_value_check<type, r>::deduce(
						(((derived_type*)0)->operator()((*(args*)0)...), details::void_exp_result<type>()))) == sizeof(yes);
	};

public:
	static const bool value = impl<call_details, has_member<type>::result>::value;
};
