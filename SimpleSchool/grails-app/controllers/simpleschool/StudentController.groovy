package simpleschool

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class StudentController {

    StudentService studentService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond studentService.list(params), model:[studentCount: studentService.count()]
    }

    def show(Long id) {
        respond studentService.get(id)
    }

    def create() {
        register(student)
        respond new Student(params)
    }

    def save(Student student) {
        unregister(student)
        register(student)

        if (student == null) {
            notFound()
            return
        }

        try {
            studentService.save(student)
        } catch (ValidationException e) {
            respond student.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'student.label', default: 'Student'), student.id])
                redirect student
            }
            '*' { respond student, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond studentService.get(id)
    }

    def update(Student student) {

        unregister(student)
        register(student)

        if (student == null) {
            notFound()
            return
        }

        try {
            studentService.save(student)
        } catch (ValidationException e) {
            respond student.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'student.label', default: 'Student'), student.id])
                redirect student
            }
            '*'{ respond student, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        studentService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'student.label', default: 'Student'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'student.label', default: 'Student'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }

    def register(Student student) {
        if(student.course1) {
            course = courseService.get(student.course1.id)
            course.students.add(student)
            course.save(flush:true)
        }
        if(student.course2) {
            course = courseService.get(student.course2.id)
            course.students.add(student)
            course.save(flush:true)
        }
        if(student.course3) {
            course = courseService.get(student.course3.id)
            course.students.add(student)
            course.save(flush:true)
        }
        if(student.course4) {
            course = courseService.get(student.course4.id)
            course.students.add(student)
            course.save(flush:true)
        }
        if(student.course5) {
            course = courseService.get(student.course5.id)
            course.students.add(student)
            course.save(flush:true)
        }
    }

    def unregister(Student student){
        if(student.course1) {
            course = courseService.get(student.course1.id)
            course.students.delete(student)
            course.save(flush:true)
        }
        if(student.course2) {
            course = courseService.get(student.course2.id)
            course.students.delete(student)
            course.save(flush:true)
        }
        if(student.course3) {
            course = courseService.get(student.course3.id)
            course.students.delete(student)
            course.save(flush:true)
        }
        if(student.course4) {
            course = courseService.get(student.course4.id)
            course.students.delete(student)
            course.save(flush:true)
        }
        if(student.course5) {
            course = courseService.get(student.course5.id)
            course.students.delete(student)
            course.save(flush:true)
        }
    }

}
