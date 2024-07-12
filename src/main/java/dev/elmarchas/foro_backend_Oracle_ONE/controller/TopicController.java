package dev.elmarchas.foro_backend_Oracle_ONE.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import dev.elmarchas.foro_backend_Oracle_ONE.domain.classroom.Classroom;
import dev.elmarchas.foro_backend_Oracle_ONE.domain.classroom.ClassroomRepository;
import dev.elmarchas.foro_backend_Oracle_ONE.domain.topic.DTOCreateTopic;
import dev.elmarchas.foro_backend_Oracle_ONE.domain.topic.DTOListTopic;
import dev.elmarchas.foro_backend_Oracle_ONE.domain.topic.DTOResponseTopic;
import dev.elmarchas.foro_backend_Oracle_ONE.domain.topic.Topic;
import dev.elmarchas.foro_backend_Oracle_ONE.domain.topic.TopicRepository;
import dev.elmarchas.foro_backend_Oracle_ONE.domain.user.UserRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/topic")
public class TopicController {

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ClassroomRepository classroomRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<DTOResponseTopic> createTopic(
            @RequestBody @Valid DTOCreateTopic datosRegistroTopic,
            UriComponentsBuilder uriComponentsBuilder) {
        System.out.println("auntes del validator");

        if (!userRepository.findById(datosRegistroTopic.userId()).isPresent()) {

            throw new ArithmeticException("user not found");
        }

        if (!classroomRepository.findById(datosRegistroTopic.classroomId()).isPresent()) {
            throw new ArithmeticException("classroom not found");
        }

        if (topicRepository.findByTitle(datosRegistroTopic.title()).isPresent()) {
            throw new ArithmeticException("That topic already exist");
        }

        if (topicRepository.findByMessage(datosRegistroTopic.message()).isPresent()) {
            throw new ArithmeticException("That topic already exist");
        }
        System.out.println("despues del validator");

        var user = userRepository.findById(datosRegistroTopic.userId()).get();
        System.out.println("despues de obtener user");
        var classroom = classroomRepository.findById(datosRegistroTopic.classroomId()).get();
        System.out.println("despues de obtener classroom");

        var topic = new Topic(
                datosRegistroTopic.title(),
                datosRegistroTopic.message(),
                user,
                classroom);

        System.out.println("creamos topico nuevo");
        System.out.println(topic.getTimeStamp());

        topicRepository.save(topic);
        DTOResponseTopic res = new DTOResponseTopic(
                topic.getId(),
                topic.getTitle(),
                topic.getMessage(),
                topic.getTimeStamp(),
                topic.getStatus(),
                user.getId(),
                classroom.getId(),
                topic.getActive());

        URI url = uriComponentsBuilder.path("/topic/{id}").buildAndExpand(topic.getId()).toUri();
        return ResponseEntity.created(url).body(res);

    }

    @GetMapping
    public ResponseEntity<Page<DTOListTopic>> listadoTopics(@PageableDefault(size = 2) Pageable paginacion) {
        // return topicRepository.findAll(paginacion).map(DatosListadoTopic::new);
        return ResponseEntity.ok(topicRepository.findByActiveTrue(paginacion).map(
                DTOListTopic::new));
    }

    /*
     * @PutMapping
     * 
     * @Transactional
     * public ResponseEntity actualizarTopic(@RequestBody @Valid
     * DatosActualizarTopic datosActualizarTopic) {
     * Topic medico = topicRepository.getReferenceById(datosActualizarTopic.id());
     * medico.actualizarDatos(datosActualizarTopic);
     * return ResponseEntity.ok(new DTOResponseTopic(medico.getId(),
     * medico.getNombre(), medico.getEmail(),
     * medico.getTelefono(), medico.getEspecialidad().toString(),
     * new DatosDireccion(medico.getDireccion().getCalle(),
     * medico.getDireccion().getDistrito(),
     * medico.getDireccion().getCiudad(), medico.getDireccion().getNumero(),
     * medico.getDireccion().getComplemento())));
     * }
     * 
     * // DELETE LOGICO
     * 
     * @DeleteMapping("/{id}")
     * 
     * @Transactional
     * public ResponseEntity eliminarTopic(@PathVariable Long id) {
     * Topic medico = topicRepository.getReferenceById(id);
     * medico.desactivarTopic();
     * return ResponseEntity.noContent().build();
     * }
     * 
     * @GetMapping("/{id}")
     * public ResponseEntity<DTOResponseTopic> retornaDatosTopic(@PathVariable Long
     * id) {
     * Topic medico = topicRepository.getReferenceById(id);
     * var datosTopic = new DTOResponseTopic(medico.getId(), medico.getNombre(),
     * medico.getEmail(),
     * medico.getTelefono(), medico.getEspecialidad().toString(),
     * new DatosDireccion(medico.getDireccion().getCalle(),
     * medico.getDireccion().getDistrito(),
     * medico.getDireccion().getCiudad(), medico.getDireccion().getNumero(),
     * medico.getDireccion().getComplemento()));
     * return ResponseEntity.ok(datosTopic);
     * }
     */
}
