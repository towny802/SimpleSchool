package simpleschool

class Course {

    String descr
    Department dept
    Integer class_num

    static hasMany = [students: Student]

    static mapping = {
    }

    static constraints = {
        students size: 0..50
        class_num unique: true
    }
}
