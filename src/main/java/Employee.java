public class Employee {
    public long id;
    public String firstName;
    public String lastName;
    public String country;
    public int age;

    public Employee() {
    }

    public Employee(long id, String firstName, String lastName, String country, int age) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.country = country;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Employee " + id + ": " + firstName + ", "
                + lastName + ", " + country + ", " + age;

    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Employee) {
            Employee e = (Employee) o;
            return this.id == e.id && this.firstName.equals(e.firstName) &&
                    this.lastName.equals(e.lastName) && this.country.equals(e.country) &&
                    this.age == e.age;
        }
        return false;
    }

}