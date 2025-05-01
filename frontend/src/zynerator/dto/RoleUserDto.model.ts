import { BaseDto } from "./BaseDto.model";
import { RoleDto } from "./RoleDto.model";
import { UserDto } from "./UserDto.model";

export class RoleUserDto extends BaseDto {

    public role: RoleDto;
    public user: UserDto;


    constructor() {
        super();

        this.role = new RoleDto();
        this.user = new UserDto();

    }

}


