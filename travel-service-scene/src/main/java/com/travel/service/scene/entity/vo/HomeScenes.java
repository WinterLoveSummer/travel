package com.travel.service.scene.entity.vo;


import com.travel.service.scene.entity.TravelScene;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.ws.BindingType;
import java.util.List;

@Data
@BindingType
@AllArgsConstructor
@NoArgsConstructor
public class HomeScenes {
    private String city;
    private List<TravelScene> scenes;
}
