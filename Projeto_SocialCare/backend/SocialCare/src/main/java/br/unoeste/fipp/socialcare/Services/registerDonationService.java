package br.unoeste.fipp.socialcare.Services;

import br.unoeste.fipp.socialcare.DataBase.entities.Parametrization;
import br.unoeste.fipp.socialcare.DataBase.repositories.parametrizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.ResourceLoader;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@Service
public class registerDonationService {
    @Autowired
    private parametrizationRepository paramRepo;
    @Autowired
    private ResourceLoader resourceLoader;

    public Parametrization addParam (Parametrization param, MultipartFile image, MultipartFile image2) throws IOException {
        File pasta_img = new File(getStaticPath()+"\\images");

        String nomeArq;
        String nomeArq2;
        nomeArq=param.getId()+"."+extensao(image.getOriginalFilename());
        nomeArq2=param.getId()+"."+extensao(image2.getOriginalFilename());

        param.setLogoG(nomeArq);
        param.setLogoP(nomeArq2);

        param = paramRepo.save(param);
        if(!pasta_img.exists())
            pasta_img.mkdir();



        Files.copy(image.getInputStream(),
                Paths.get(pasta_img.getAbsolutePath()).resolve(nomeArq), StandardCopyOption.REPLACE_EXISTING);
        Files.copy(image.getInputStream(),
                Paths.get(pasta_img.getAbsolutePath()).resolve(nomeArq2), StandardCopyOption.REPLACE_EXISTING);

        return param;
    }

    public Parametrization editParam(Parametrization param){
        return paramRepo.save(param);
    }

    public boolean deleteById (Long id) {
        try{
            paramRepo.deleteById(id);
        }
        catch (Exception e){
            return false;
        }
        return true;
    }

    public Parametrization getById (Long id) {
        Parametrization param = paramRepo.findById(id).get();
        return param;
    }

    public boolean getByIdB(Long id){
        Parametrization param = paramRepo.findById(id).get();
        return param!=null;
    }

    public List<Parametrization> getAll() {
        return paramRepo.findAll();
    }

    public String getStaticPath() throws IOException {
        String staticPath;
        staticPath = resourceLoader.getResource("classpath:static").getFile().getAbsolutePath();
        return staticPath;
    }

    private String extensao(String extensao)
    {
        int lastIndex = extensao.lastIndexOf(".");
        return  extensao.substring(lastIndex + 1);
    }
}
