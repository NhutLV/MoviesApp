package training.fpt.nhutlv.newsapp.tools;

import training.fpt.nhutlv.newsapp.entities.TypeImage;
import training.fpt.nhutlv.newsapp.model.service.TypeImageServiceImpl;
import training.fpt.nhutlv.newsapp.utils.Callback;

/**
 * Created by NhutDu on 22/12/2016.
 */

public class URLImage {

    private TypeImage mTypeImage = new TypeImage();

    public  TypeImage getURLImage(){
        TypeImageServiceImpl service = new TypeImageServiceImpl();

        service.getImageURL(new Callback<TypeImage>() {
            @Override
            public void onResult(TypeImage typeImage) {
                mTypeImage = typeImage;
            }
        });
        return mTypeImage;
    }

}
