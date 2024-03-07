package com.prenotapp._service.impl;

import com.prenotapp._dto.AppointmentDTO;
import com.prenotapp._dto.AppointmentDetailsDTO;
import com.prenotapp._model.Appointment;
import com.prenotapp._repo.IAppointmentRepo;
import com.prenotapp._service.IAppointmentService;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;
import lombok.NonNull;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@SuppressWarnings("null")
public class AppointmentServiceImpl implements IAppointmentService {

  @Autowired
  private IAppointmentRepo repo;

  @Autowired
  private ModelMapper mapper;

  @Override
  @Transactional(readOnly = true)
  public List<AppointmentDetailsDTO> list() throws Exception {
    return repo
      .findAll()
      .stream()
      .map(this::toDTO)
      .collect(Collectors.toList());
  }

  @Override
  @Transactional(readOnly = true)
  public AppointmentDetailsDTO findById(@NonNull Long idAppointment) {
    return toDTO(repo.findById(idAppointment).get());
  }

  @Override
  public boolean appointmentAlreadyExist(AppointmentDTO appointment) {
    return repo.existsByPersonaIdAndShopIdAndDate(
      appointment.getPersona().getId(),
      appointment.getShop().getId(),
      appointment.getDate()
    );
  }

  @Override
  @Transactional
  public AppointmentDetailsDTO register(AppointmentDTO appointment)
    throws Exception {
    return toDTO(repo.save(toEntity(appointment)));
  }

  @Override
  @Transactional
  public AppointmentDetailsDTO update(AppointmentDTO appointment)
    throws Exception {
    return toDTO(repo.save(toEntity(appointment)));
  }
  @Override
  @Transactional
  public void delete(@NonNull Long id) throws Exception {
    repo.deleteById(id);
  }

  private AppointmentDetailsDTO toDTO(Appointment appointment) {
    if (appointment == null) {
      return null;
    }

    mapper.addConverter(
      new Converter<LocalDateTime, String>() {
        @Override
        public String convert(MappingContext<LocalDateTime, String> context) {
          LocalDateTime date = context.getSource();
          DateTimeFormatter formatter = DateTimeFormatter.ofPattern(
            "dd-MM-yyyy, HH:mm"
          );
          return date.format(formatter);
        }
      }
    );

    return mapper.map(appointment, AppointmentDetailsDTO.class);
  }

  private Appointment toEntity(AppointmentDTO appointment) {
    if (appointment == null) {
      return null;
    }

    return mapper.map(appointment, Appointment.class);
  }
}
