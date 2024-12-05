package dgu.cse.newsee.app.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BannerDto {

    private String imageUrl;
    private String title;
    private String shorts;

}
