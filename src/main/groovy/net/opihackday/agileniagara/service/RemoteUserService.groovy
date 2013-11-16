package net.opihackday.agileniagara.service

/**
 * User: danielwoods
 * Date: 11/16/13
 */
public interface RemoteUserService {

  Map getUserByEmail()

  void createUser(Map user)
}