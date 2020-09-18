package utils;

public interface ModelMapper<Model,Dto> {
    Model dtoToModel(Dto Dto);
    Dto modelToDto(Model model);
}
