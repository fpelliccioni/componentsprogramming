#include <iostream>
#include <string>

#include "min.hpp"

void usage_with_builtin_types_simple_1() {

    int a = 12;
    int b = 34;
 
    //using variables
    std::cout << min(a, b) << std::endl;
 
    //using integer-literals (base10)
    std::cout << min(1, 2) << std::endl;
 
    //using variables and literals
    std::cout << min(a, 2)  << std::endl;
 
    //assigning the result (copy)
    int m1 = min(a, b);
    int m2 = min(1, 2);
}

// -----------------------------------

struct employee1 { std::string name; float salary; };

void usage_with_employees1() {
    employee1 e1 {"John", 5000.0f};
    employee1 e2 {"Peter", 6000.0f};
    employee1 e3 {"George", 4500.0f};
    employee1 e4 {"Frank", 5000.0f};
 
    // employee1 m = min(e1, e2); // Compile-time Error
}

// -----------------------------------

struct employee { int id; std::string name; float salary; };

bool operator<(employee const& a, employee const& b) {
    return a.id < b.id;
}

struct salary_comparator {
    bool operator()(employee const& a, employee const& b) const {
        return a.salary < b.salary;
    }
};

void usage_with_employees_and_predicates() {
    employee e1 {1, "John", 5000.0f};
    employee e2 {2, "Peter", 6000.0f};
    employee e3 {3, "George", 4500.0f};
    employee e4 {4, "Frank", 5000.0f};
 
    employee m = min(e1, e2);
    std::cout << m.name << std::endl;
     
    employee m2 = min(e1, e2, salary_comparator{});
    std::cout << m2.name << std::endl; 

    employee m3 = min(e1, e2, [](employee const& a, employee const& b){
              return a.name < b.name; } );
    std::cout << m3.name << std::endl;

    employee m4 = min(e1, e2, [](auto const& a, auto const& b){
              return a.name < b.name; } );
    std::cout << m4.name << std::endl;

}

// -----------------------------------


int main(int argc, char const *argv[])
{
	usage_with_builtin_types_simple_1();
	usage_with_employees1();
	usage_with_employees_and_predicates();

	return 0;
}
